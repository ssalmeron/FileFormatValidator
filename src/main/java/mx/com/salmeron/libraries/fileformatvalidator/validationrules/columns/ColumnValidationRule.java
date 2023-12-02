package mx.com.salmeron.libraries.fileformatvalidator.validationrules.columns;

/**
 * Represents a validation rule applicable to a column of a line from a file.
 * The {@link ColumnValidationRule#validate(String)} implementation applies the rules to the text parameter
 *
 * @author Sergio Salmeron
 */
public interface ColumnValidationRule {


    /**
     * Contains the validation logic for a column validation rule
     * @param text Column of the file
     * @return True if the text parameter complies with the validation rule
     * @throws Exception return if the text does not comply with the validation rule
     */
    boolean validate(String text) throws Exception;
}
