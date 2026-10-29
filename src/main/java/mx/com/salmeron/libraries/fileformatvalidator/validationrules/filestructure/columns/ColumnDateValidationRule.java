package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidDateException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Validation rule to verify that a column is a valid date (or date-time) for the given pattern.
 * The validation is strict: e.g. {@code 20240230} is rejected for {@code uuuuMMdd}.
 * Use {@code u} instead of {@code y} for the year in the pattern, as strict resolution requires it.
 */
@ToString
public class ColumnDateValidationRule implements ColumnValidationRule {

    private final String pattern;

    @ToString.Exclude
    private final DateTimeFormatter formatter;

    public ColumnDateValidationRule(String pattern) {
        this.pattern = pattern;
        this.formatter = DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
    }

    @Override
    public boolean validate(String text) throws Exception {

        try {
            formatter.parse(text);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(text, pattern);
        }

        return true;
    }
}
