package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

import java.util.Set;

/**
 * Thrown when a column is not one of the allowed values.
 */
public class ValueNotAllowedException extends Exception {
    private static final long serialVersionUID = -6192837465019283746L;

    public ValueNotAllowedException(String text, Set<String> allowedValues) {
        super("Value not allowed: " + text + ", Allowed values: " + allowedValues);
    }
}
