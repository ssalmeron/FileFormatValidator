package mx.com.salmeron.libraries.fileformatvalidator.validationrules;

import lombok.ToString;

import java.util.regex.Pattern;

/**
 * Class with common operation on Strings
 */
@ToString
public class StringUtils {

    private StringUtils() {
    }

    /**
     * Separate the fields from a line using the field separator provided.
     * Empty fields are preserved, e.g. {@code "a||c"} with {@code "|"} returns 3 fields,
     * and the separator is matched literally (it is not treated as a regex or a set of characters).
     *
     * @param text           Line of text from the file
     * @param fieldSeparator Text used to separate fields in a line of text
     * @return Array of Strings representing the collection of fields
     */
    public static String[] tokenizeString(String text, String fieldSeparator) {
        return text.split(Pattern.quote(fieldSeparator), -1);
    }
}
