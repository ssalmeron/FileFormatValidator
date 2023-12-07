package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineFieldSeparatorValidationRule;

/**
 * Error when the field separator is more than one character
 * @see LineFieldSeparatorValidationRule
 * @author Sergio Salmeron
 * @version 1.0
 * @since 1.0
 */
public class InvalidFieldSeparatorLengthException extends Exception{
    private static final long serialVersionUID = -6104060326943146520L;

    public InvalidFieldSeparatorLengthException() {
        super("Invalid field separator length");

    }
}
