package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line;

import lombok.AllArgsConstructor;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.StringUtils;

/**
 * Validation rule to verify if the given text line has the specified number of columns.
 * A field separator must be provided to calculate the number of columns
 *
 * @version 1.0
 * @since 1.0
 * @see LineValidationRule
 * @author Sergio Salmeron
 */
@ToString
@AllArgsConstructor
public final class LineNumberOfColumnsValidationRule implements LineValidationRule{

    private int numberOfColumns;
     private String fieldSeparator;



    @Override
    public boolean validate(String text) throws Exception {
        return StringUtils.tokenizeString(text, fieldSeparator).length == numberOfColumns;
    }
}
