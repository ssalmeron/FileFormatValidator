package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.errors.InvalidFieldSeparatorLengthException;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Validation rule
 *
 * @version 1.0
 * @since 1.0
 * @see LineValidationRule
 * @author Sergio Salmeron
 */
@ToString
@AllArgsConstructor
public class LineFieldSeparatorValidationRule implements LineValidationRule {

    private String fieldSeparator;

    @Override
    public boolean validate(String text) throws InvalidFieldSeparatorLengthException {

        if (validateSeparator(fieldSeparator)) {
            String[] splitedString = splitString(text, fieldSeparator);
            return splitedString.length > 1;
        }
        return false;
    }

    /**
     * Validate the field separator provided
     * @param fieldSeparator A one character used to separate field in a line of text
     * @return True if the field separator is one character in length
     * @throws InvalidFieldSeparatorLengthException Thrown if the field separator is more than one character
     *
     */
    private boolean validateSeparator(String fieldSeparator) throws InvalidFieldSeparatorLengthException {

        if (fieldSeparator.trim().length() > 1) {
            throw new InvalidFieldSeparatorLengthException();
        }

        return true;
    }

    /**
     * Separate the fields from a line using the field separator provided
     * @param text Line of text from the file
     * @param fieldSeparator A one character used to separate field in a line of text
     * @return Array of Strings representing the collection of fields
     */
    private String[] splitString(String text, String fieldSeparator) {



        StringTokenizer stringTokenizer = new StringTokenizer(text, fieldSeparator);
        ArrayList<String> tokensStr = new ArrayList<>(text.length());
        while (stringTokenizer.hasMoreTokens()) {
            tokensStr.add(stringTokenizer.nextToken());
        }
        return tokensStr.toArray(new String[0]);


    }


}
