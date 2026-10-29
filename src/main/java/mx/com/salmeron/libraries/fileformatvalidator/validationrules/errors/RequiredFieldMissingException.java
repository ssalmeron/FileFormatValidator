package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

/**
 * Thrown when a required column is empty or blank.
 */
public class RequiredFieldMissingException extends Exception {
    private static final long serialVersionUID = 4518293037461298401L;

    public RequiredFieldMissingException() {
        super("Required field is empty");
    }
}
