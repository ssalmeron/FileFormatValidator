package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.RequiredFieldMissingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Column Required Validation Test")
class ColumnRequiredValidationRuleTest {

    private final ColumnValidationRule rule = new ColumnRequiredValidationRule();

    @Test
    @DisplayName("Non empty value is valid")
    void validateNonEmpty() throws Exception {
        assertTrue(rule.validate("a"));
    }

    @Test
    @DisplayName("Empty and blank values are invalid")
    void validateEmpty() {
        assertThrows(RequiredFieldMissingException.class, () -> rule.validate(""));
        assertThrows(RequiredFieldMissingException.class, () -> rule.validate("   "));
    }
}
