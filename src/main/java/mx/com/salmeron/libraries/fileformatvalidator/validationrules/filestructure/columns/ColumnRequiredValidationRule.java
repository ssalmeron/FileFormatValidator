package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.RequiredFieldMissingException;

/**
 * Validation rule to verify that a column is not empty or blank
 */
@ToString
public class ColumnRequiredValidationRule implements ColumnValidationRule {

    @Override
    public boolean validate(String text) throws Exception {

        if (text == null || text.trim().isEmpty()) {
            throw new RequiredFieldMissingException();
        }

        return true;
    }
}
