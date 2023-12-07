package mx.com.salmeron.libraries.fileformatvalidator;

import lombok.AllArgsConstructor;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.StringUtils;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.ErrorWhileReadingSectionException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.NumberOfColumnsDoesNotMatchException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.Field;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.FileStructure;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineFieldSeparatorValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineLengthValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineValidationRule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>File Validator</h1>
 * The {@link FileValidator} is the main class for validating the format of a file
 * <p>
 * To initialize a FileValidation you must provide:
 * <ol>
 *     <li><p>A list of {@link LineValidationRule}s in the constructor {@link FileValidator#FileValidator(List)}</p></li>
 *     <li>A String path of a file to be validated in the {@link FileValidator#validate(String)} method</li>
 * </ol>
 * </p>
 * <p>
 *     <h3>Example</h3>
 * <p>
 *     Define the file name to be validated:
 * <pre>
 *     String fileName = "testingFiles/AllLines10.txt";
 * </pre>
 *     Get the filepath
 * <pre>
 *     Optional<URL> resource = Optional.ofNullable(this.getClass().getClassLoader().getResource(fileName));
 * </pre>
 *      Create a few {@linkplain LineValidationRule}
 *
 * <pre>
 *       //Validation Rules list
 *       List<LineValidationRule> lineValidationRules = new ArrayList<>();
 *
 *       // Length Validation rule of size 10
 *       LineValidationRule lengthValidation = new LineLengthValidationRule(10);
 * </pre>
 *       Add the validation Rules to the {@link FileValidator}
 *
 * <pre>
 *        FileValidator validator = new FileValidator(lineValidationRules);
 * </pre>
 *        Validate the file
 * <pre>
 *     validator.validate(resource.get().getPath());
 * </pre>
 * </p>
 *
 * @author Sergio Salmeron
 * @version 1.0
 * @since 1.0
 * @see LineValidationRule
 * @see LineLengthValidationRule
 * @see LineFieldSeparatorValidationRule
 *
 */
@ToString
@AllArgsConstructor
public class FileValidator {

    private static final Logger logger = LogManager.getLogger(FileValidator.class);

    private List<LineValidationRule> lineValidationRules;
    private FileStructure fileStructure =  new FileStructure();

    public FileValidator(List<LineValidationRule> lineValidationRules) {
        logger.atInfo().log("Initializing FileValidator Object with Line Validation Rules: " + lineValidationRules);
        this.lineValidationRules = lineValidationRules;
    }

    public FileValidator(FileStructure fileStructure) {
        logger.atInfo().log("Initializing FileValidator Object with File structure: " + fileStructure);
        this.fileStructure = fileStructure;
    }

    /**
     * Main method to validate the files format
     * @param path of the file to be validated
     * @return Boolean if file complies with all ValidationRules
     * @throws Exception returned of failed validation
     */
    public boolean validate(String path) throws Exception {
        try (FileReader fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr)) {

            String line;

            while ((line = br.readLine()) != null) {
                for (LineValidationRule rule : lineValidationRules) {
                    if (!rule.validate(line)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * VAlidate the give file against the FileStructure definition
     * @param path of the file to be validated
     * @return if file complies with all ValidationRules
     * @throws Exception returned of failed validation
     */
    public boolean validateFileStructure(String path) throws Exception {

        boolean contentValid = false;

        try (FileReader fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr)) {
            List<String> readContentSection = readLinesFromSection(br, fileStructure.getContentLine().getMaxLines());
             contentValid = validateContent(readContentSection);
        }

        return contentValid;


    }

    private List<String> readLinesFromSection(BufferedReader br, int maxLines) throws ErrorWhileReadingSectionException {

    List<String> linesFromSection = new ArrayList<>();
    int lineNumber = 0;

    try {
        while (br.ready() && lineNumber < maxLines) {
            linesFromSection.add(br.readLine());
            lineNumber++;
        }
    } catch (IOException e) {
        logger.atError().log("Error while reading section " + lineNumber + ": " + e.getMessage());
        throw new ErrorWhileReadingSectionException();
    }

    return linesFromSection;
}




    /**
     * VValidate the content section of the file structure
     * @param readContentSection
     * @return
     * @throws NumberOfColumnsDoesNotMatchException if any of the rules are not met
     */
    private boolean validateContent(List<String> readContentSection) throws Exception {

        int numberOfFields = fileStructure.getContentLine().getFields().size();

        for (String line : readContentSection){


            String[] lineColumns = StringUtils.tokenizeString(line, fileStructure.getFieldSeparator());

            int tokenizedStringNumColumns = lineColumns.length; //number of columns based on the given field separator

            //Validate that the number of columns from the fileStructure matches with the number of columns in the line.

            if(numberOfFields != tokenizedStringNumColumns) {
                logger.atError().log("The number of columns from the file (" + numberOfFields + ") does not match with the number of columns defined in the file structure (" + tokenizedStringNumColumns + ").");
                throw new NumberOfColumnsDoesNotMatchException(numberOfFields, tokenizedStringNumColumns);

            }


            for (int indexCol= 0; indexCol< lineColumns.length; indexCol++) {  //Iterate through all the columns from the line of the file


                String lineColumn= lineColumns[indexCol];
                Field validationField = fileStructure.getContentLine().getFields().get(indexCol);

                for (ColumnValidationRule columnValidationRule : validationField.getValidationRules()){ //Iterate through all validation rules added for the given columns in the file structure
                    columnValidationRule.validate(lineColumn);

                }
            }

        }

        return true;
    }
}
