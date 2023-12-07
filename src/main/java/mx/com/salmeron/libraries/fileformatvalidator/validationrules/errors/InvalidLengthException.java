package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

public class InvalidLengthException extends Exception {
    private static final long serialVersionUID = -7829291270128335209L;

    public InvalidLengthException(int textLength, int requiredLength) {
        super("Invalid length: Text provided length: " + textLength + ", Required length: " + requiredLength);
    }
}
