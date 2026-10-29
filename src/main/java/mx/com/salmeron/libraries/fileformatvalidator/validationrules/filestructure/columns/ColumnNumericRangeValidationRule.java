package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import lombok.AllArgsConstructor;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidNumberException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.ValueOutOfRangeException;

import java.math.BigDecimal;

/**
 * Validation rule to verify that a numeric column is between a minimum and a maximum (both inclusive)
 */
@ToString
@AllArgsConstructor
public class ColumnNumericRangeValidationRule implements ColumnValidationRule {

    private BigDecimal min;
    private BigDecimal max;

    public ColumnNumericRangeValidationRule(long min, long max) {
        this(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
    }

    @Override
    public boolean validate(String text) throws Exception {

        BigDecimal value;
        try {
            value = new BigDecimal(text.trim());
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(text);
        }

        if (value.compareTo(min) < 0 || value.compareTo(max) > 0) {
            throw new ValueOutOfRangeException(text, min.toPlainString(), max.toPlainString());
        }

        return true;
    }
}
