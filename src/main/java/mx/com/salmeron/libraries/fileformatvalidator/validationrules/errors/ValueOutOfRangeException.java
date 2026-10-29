package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

/**
 * Thrown when a numeric column is outside of the allowed range.
 */
public class ValueOutOfRangeException extends Exception {
    private static final long serialVersionUID = -2938475610293847561L;

    public ValueOutOfRangeException(String text, String min, String max) {
        super("Value out of range: " + text + ", Allowed range: [" + min + ", " + max + "]");
    }
}
