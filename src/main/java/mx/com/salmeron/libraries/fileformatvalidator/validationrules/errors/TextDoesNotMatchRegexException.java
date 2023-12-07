package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

import java.util.regex.Pattern;

public class TextDoesNotMatchRegexException extends Exception {
    private static final long serialVersionUID = 3934761815728292966L;

    public TextDoesNotMatchRegexException(String text, Pattern regex) {
        super("Regex validation failed: " + text + "with: " +regex.pattern());
    }
}
