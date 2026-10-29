package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

/**
 * Thrown when a column is not a valid number.
 */
public class InvalidNumberException extends Exception {
    private static final long serialVersionUID = 7720381946251738420L;

    public InvalidNumberException(String text) {
        super("Invalid number: " + text);
    }
}
