package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

/**
 * Thrown when a column is not a valid date for the given format.
 */
public class InvalidDateException extends Exception {
    private static final long serialVersionUID = 5832019475610293874L;

    public InvalidDateException(String text, String pattern) {
        super("Invalid date: " + text + ", Required format: " + pattern);
    }
}
