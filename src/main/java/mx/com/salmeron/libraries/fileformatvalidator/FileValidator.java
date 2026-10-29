package mx.com.salmeron.libraries.fileformatvalidator;

import lombok.AllArgsConstructor;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.result.ValidationError;
import mx.com.salmeron.libraries.fileformatvalidator.result.ValidationResult;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.StringUtils;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.MethodNotImplementedException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.NumberOfColumnsDoesNotMatchException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.ContentLine;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.Field;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.FileStructure;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.HeaderLine;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineFieldSeparatorValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineLengthValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineValidationRule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>File Validator</h1>
 * The {@link FileValidator} is the main class for validating the format of a file
 * <p>
 * To initialize a FileValidation you must provide:
 * <ol>
 *     <li><p>A list of {@link LineValidationRule}s in the constructor {@link FileValidator#FileValidator(List)}</p></li>
 *     <li>A String path of a file to be validated in the {@link FileValidator#validateLines(String)} method</li>
 * </ol>
 * or
 * <ol>
 *     <li><p>A {@link FileStructure} in the constructor {@link FileValidator#FileValidator(FileStructure)}</p></li>
 *     <li>A String path of a file to be validated in the {@link FileValidator#validateStructure(String)} method</li>
 * </ol>
 * Both methods return a {@link ValidationResult} with every error found in the file.
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
 *     ValidationResult result = validator.validateLines(resource.get().getPath());
 *     result.getErrors().forEach(System.out::println);
 * </pre>
 * </p>
 *
 * @author Sergio Salmeron
 * @version 1.1
 * @since 1.0
 * @see LineValidationRule
 * @see LineLengthValidationRule
 * @see LineFieldSeparatorValidationRule
 * @see ValidationResult
 *
 */
@ToString
@AllArgsConstructor
public class FileValidator {

    private static final Logger logger = LogManager.getLogger(FileValidator.class);

    private List<LineValidationRule> lineValidationRules = new ArrayList<>();
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
     * Validate the file against the {@link LineValidationRule}s and stop at the first error.
     * @param path of the file to be validated
     * @return Boolean if file complies with all ValidationRules
     * @throws Exception thrown by a {@link LineValidationRule}
     * @deprecated use {@link #validateLines(String)} to get every error found in the file
     */
    @Deprecated
    public boolean validate(String path) throws Exception {
        ValidationResult result = validateLines(path, StandardCharsets.UTF_8, 1);
        throwFirstErrorCause(result);
        return result.isValid();
    }

    /**
     * Validate every line of the file (read as UTF-8) against the {@link LineValidationRule}s
     * @param path of the file to be validated
     * @return the {@link ValidationResult} with every error found
     * @throws IOException if the file can not be read
     */
    public ValidationResult validateLines(String path) throws IOException {
        return validateLines(path, StandardCharsets.UTF_8, ValidationResult.UNLIMITED_ERRORS);
    }

    /**
     * Validate every line of the file against the {@link LineValidationRule}s
     * @param path of the file to be validated
     * @param charset of the file
     * @param maxErrors maximum number of errors to collect before stopping, 0 for unlimited
     * @return the {@link ValidationResult} with the errors found
     * @throws IOException if the file can not be read
     */
    public ValidationResult validateLines(String path, Charset charset, int maxErrors) throws IOException {
        ValidationResult result = new ValidationResult(maxErrors);

        try (BufferedReader br = newReader(path, charset)) {

            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null && !result.isMaxErrorsReached()) {
                lineNumber++;
                for (LineValidationRule rule : lineValidationRules) {
                    String ruleName = rule.getClass().getSimpleName();
                    try {
                        if (!rule.validate(line)) {
                            result.addError(new ValidationError(lineNumber, ValidationError.LINE_LEVEL, null, line,
                                    ruleName, "Line does not comply with " + rule, null));
                        }
                    } catch (Exception e) {
                        result.addError(new ValidationError(lineNumber, ValidationError.LINE_LEVEL, null, line,
                                ruleName, e.getMessage(), e));
                    }
                }
            }
        }
        return result;
    }

    /**
     * Validate the given file against the FileStructure definition and stop at the first error.
     * @param path of the file to be validated
     * @return if file complies with all ValidationRules
     * @throws Exception the exception of the first failed validation
     * @deprecated use {@link #validateStructure(String)} to get every error found in the file
     */
    @Deprecated
    public boolean validateFileStructure(String path) throws Exception {
        ValidationResult result = validateStructure(path, StandardCharsets.UTF_8, 1);
        throwFirstErrorCause(result);
        if (!result.isValid()) {
            throw new Exception(result.getErrors().get(0).getMessage());
        }
        return true;
    }

    /**
     * Validate the given file (read as UTF-8) against the FileStructure definition
     * @param path of the file to be validated
     * @return the {@link ValidationResult} with every error found
     * @throws IOException if the file can not be read
     */
    public ValidationResult validateStructure(String path) throws IOException {
        return validateStructure(path, StandardCharsets.UTF_8, ValidationResult.UNLIMITED_ERRORS);
    }

    /**
     * Validate the given file against the FileStructure definition.
     * The first lines are validated against the {@link HeaderLine}s (in order),
     * the remaining lines are validated against the {@link ContentLine}.
     * @param path of the file to be validated
     * @param charset of the file
     * @param maxErrors maximum number of errors to collect before stopping, 0 for unlimited
     * @return the {@link ValidationResult} with the errors found
     * @throws IOException if the file can not be read
     */
    public ValidationResult validateStructure(String path, Charset charset, int maxErrors) throws IOException {
        ValidationResult result = new ValidationResult(maxErrors);

        try (BufferedReader br = newReader(path, charset)) {
            int lineNumber = validateHeaders(br, result);
            validateContent(br, lineNumber, result);
        }

        return result;
    }

    /**
     * Filters valid rows from a file at the specified path.
     *
     * @param path the path of the file to be processed
     * @throws MethodNotImplementedException if the method is not yet implemented
     */
    public void filterValidRows(String path) throws MethodNotImplementedException {
        throw new MethodNotImplementedException("Method not implemented yet");
    }

    /**
     * Validate the header section of the file structure
     * @return the number of lines read
     */
    private int validateHeaders(BufferedReader br, ValidationResult result) throws IOException {

        int lineNumber = 0;

        for (HeaderLine headerLine : fileStructure.getHeaderLines()) {
            if (result.isMaxErrorsReached()) {
                break;
            }

            String line = br.readLine();
            lineNumber++;

            if (line == null) {
                result.addError(new ValidationError(lineNumber, ValidationError.LINE_LEVEL, null, null,
                        "HeaderLine", "Missing header line with code: " + headerLine.getLineCode(), null));
                break;
            }

            if (!line.startsWith(headerLine.getLineCode())) {
                result.addError(new ValidationError(lineNumber, ValidationError.LINE_LEVEL, null, line,
                        "HeaderLine", "Header line does not start with code: " + headerLine.getLineCode(), null));
                continue;
            }

            validateFields(lineNumber, line, headerLine.getFields(), result);
        }

        return lineNumber;
    }

    /**
     * Validate the content section of the file structure
     */
    private void validateContent(BufferedReader br, int lineNumber, ValidationResult result) throws IOException {

        ContentLine contentLine = fileStructure.getContentLine();
        if (contentLine == null) {
            return;
        }

        int maxLines = contentLine.getMaxLines();
        int contentLines = 0;
        String line;

        while (!result.isMaxErrorsReached() && (line = br.readLine()) != null) {
            lineNumber++;
            contentLines++;

            if (maxLines > 0 && contentLines > maxLines) {
                result.addError(new ValidationError(lineNumber, ValidationError.LINE_LEVEL, null, line,
                        "MaxLines", "The file has more content lines than the maximum allowed (" + maxLines + ")", null));
                break;
            }

            validateFields(lineNumber, line, contentLine.getFields(), result);
        }
    }

    /**
     * Validate the columns of a line against the field definitions
     */
    private void validateFields(int lineNumber, String line, List<Field> fields, ValidationResult result) {

        String[] lineColumns = StringUtils.tokenizeString(line, fileStructure.getFieldSeparator());

        //Validate that the number of columns from the fileStructure matches with the number of columns in the line.
        if (fields.size() != lineColumns.length) {
            NumberOfColumnsDoesNotMatchException e = new NumberOfColumnsDoesNotMatchException(fields.size(), lineColumns.length);
            logger.atError().log("Line " + lineNumber + ": " + e.getMessage());
            result.addError(new ValidationError(lineNumber, ValidationError.LINE_LEVEL, null, line,
                    "NumberOfColumns", e.getMessage(), e));
            return;
        }

        for (int indexCol = 0; indexCol < lineColumns.length; indexCol++) {  //Iterate through all the columns from the line of the file

            String lineColumn = lineColumns[indexCol];
            Field validationField = fields.get(indexCol);

            if (!validationField.isRequired() && lineColumn.isEmpty()) {
                continue;
            }

            for (ColumnValidationRule columnValidationRule : validationField.getValidationRules()) { //Iterate through all validation rules added for the given columns in the file structure
                try {
                    columnValidationRule.validate(lineColumn);
                } catch (Exception e) {
                    result.addError(new ValidationError(lineNumber, indexCol, validationField.getFieldName(), lineColumn,
                            columnValidationRule.getClass().getSimpleName(), e.getMessage(), e));
                }
            }
        }
    }

    private static BufferedReader newReader(String path, Charset charset) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(path), charset));
    }

    /**
     * Rethrow the exception of the first error, if the rule that failed threw one
     */
    private static void throwFirstErrorCause(ValidationResult result) throws Exception {
        if (!result.isValid() && result.getErrors().get(0).getCause() != null) {
            throw result.getErrors().get(0).getCause();
        }
    }
}
