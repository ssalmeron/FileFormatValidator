package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import lombok.AllArgsConstructor;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidLengthException;

/**
 * Validation rule to verify that the length of a column is between a minimum and a maximum (both inclusive)
 */
@ToString
@AllArgsConstructor
public class ColumnMinMaxLengthValidationRule implements ColumnValidationRule {

    private int minLength;
    private int maxLength;

    @Override
    public boolean validate(String text) throws Exception {

        if (text.length() < minLength || text.length() > maxLength) {
            throw new InvalidLengthException(text.length(), minLength, maxLength);
        }

        return true;
    }
}
