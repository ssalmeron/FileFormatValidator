package mx.com.salmeron.libraries.fileformatvalidator;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineFieldSeparatorValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineLengthValidationRule;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.FileReader;
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
    private List<LineValidationRule> lineValidationRules;

    /**
     * Main method to validate the files format
     * @param path of the file to be validated
     * @return Boolean if vile complies with all ValidationRules
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
}
