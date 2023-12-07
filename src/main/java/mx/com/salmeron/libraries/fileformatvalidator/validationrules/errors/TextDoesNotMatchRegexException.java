package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

import java.util.regex.Pattern;

/**
 * Custom exception class used to indicate that a string does not match a given regular expression.
 * This exception can be thrown when performing text validation using regular expressions.
 */
public class TextDoesNotMatchRegexException extends Exception {
    private static final long serialVersionUID = 3934761815728292966L;


    public TextDoesNotMatchRegexException(String text, Pattern regex) {
        super("Regex validation failed: " + text + "with: " +regex.pattern());
    }
}
