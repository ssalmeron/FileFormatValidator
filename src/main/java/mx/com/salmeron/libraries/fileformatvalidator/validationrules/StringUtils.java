package mx.com.salmeron.libraries.fileformatvalidator.validationrules;

import lombok.ToString;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Class with common operation on Strings
 */
@ToString
public class StringUtils {

    private StringUtils() {
    }

    /**
     * Separate the fields from a line using the field separator provided
     *
     * @param text           Line of text from the file
     * @param fieldSeparator A one character used to separate field in a line of text
     * @return Array of Strings representing the collection of fields
     */
    public static String[] tokenizeString(String text, String fieldSeparator) {


        StringTokenizer stringTokenizer = new StringTokenizer(text, fieldSeparator);
        ArrayList<String> tokensStr = new ArrayList<>(text.length());
        while (stringTokenizer.hasMoreTokens()) {
            tokensStr.add(stringTokenizer.nextToken());
        }
        return tokensStr.toArray(new String[0]);


    }
}
