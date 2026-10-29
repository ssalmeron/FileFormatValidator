package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import lombok.AllArgsConstructor;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidNumberException;

import java.util.regex.Pattern;

/**
 * Validation rule to verify that a column is a number.
 * An optional leading sign is allowed. Decimals (using '.') are only allowed if {@link #allowDecimals} is true.
 */
@ToString
@AllArgsConstructor
public class ColumnNumericValidationRule implements ColumnValidationRule {

    private static final Pattern INTEGER_PATTERN = Pattern.compile("[+-]?\\d+");
    private static final Pattern DECIMAL_PATTERN = Pattern.compile("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)");

    private boolean allowDecimals;

    @Override
    public boolean validate(String text) throws Exception {

        Pattern pattern = allowDecimals ? DECIMAL_PATTERN : INTEGER_PATTERN;
        if (!pattern.matcher(text).matches()) {
            throw new InvalidNumberException(text);
        }

        return true;
    }
}
