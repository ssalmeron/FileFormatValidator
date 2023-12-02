package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line;

/**
 * Represents a validation rule applicable to a line of a file.
 * The {@link LineValidationRule#validate(String)} implementation applies the rules to the text parameter
 *
 * @author Sergio Salmeron
 */
public interface LineValidationRule {

    /**
     * Contains the validation logic for a Line validation rule
     * @param text Line of the file
     * @return True if the text parameter complies with the validation rule
     * @throws Exception return if the text does not comply with the validation rule
     */

    boolean validate(String text) throws Exception;

}
