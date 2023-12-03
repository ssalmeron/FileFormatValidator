package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line;

import lombok.AllArgsConstructor;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.StringUtils;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.errors.InvalidFieldSeparatorLengthException;

/**
 * Validation rule to verify if the given text line has the specified field separator
 *
 * @author Sergio Salmeron
 * @version 1.0
 * @see LineValidationRule
 * @since 1.0
 */
@ToString
@AllArgsConstructor
public final class LineFieldSeparatorValidationRule implements LineValidationRule {

    private String fieldSeparator;

    @Override
    public boolean validate(String text) throws InvalidFieldSeparatorLengthException {

        validateSeparator(fieldSeparator);
        String[] tokenizedString = StringUtils.tokenizeString(text, fieldSeparator);
        return tokenizedString.length > 1;

    }

    /**
     * Validate the field separator provided
     *
     * @param fieldSeparator A one character used to separate field in a line of text
     * @throws InvalidFieldSeparatorLengthException Thrown if the field separator is more than one character
     */
    private void validateSeparator(String fieldSeparator) throws InvalidFieldSeparatorLengthException {

        if (fieldSeparator.trim().length() > 1) {
            throw new InvalidFieldSeparatorLengthException();
        }

    }


}
