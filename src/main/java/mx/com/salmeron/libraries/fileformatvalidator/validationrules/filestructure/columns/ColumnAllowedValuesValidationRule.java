package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.ValueNotAllowedException;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Validation rule to verify that a column is one of the allowed values (case-sensitive)
 */
@ToString
public class ColumnAllowedValuesValidationRule implements ColumnValidationRule {

    private final Set<String> allowedValues;

    public ColumnAllowedValuesValidationRule(Set<String> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public ColumnAllowedValuesValidationRule(String... allowedValues) {
        this(new LinkedHashSet<>(Arrays.asList(allowedValues)));
    }

    @Override
    public boolean validate(String text) throws Exception {

        if (!allowedValues.contains(text)) {
            throw new ValueNotAllowedException(text, allowedValues);
        }

        return true;
    }
}
