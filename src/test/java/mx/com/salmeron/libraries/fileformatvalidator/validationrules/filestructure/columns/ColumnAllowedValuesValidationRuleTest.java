package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.ValueNotAllowedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Column Allowed Values Validation Test")
class ColumnAllowedValuesValidationRuleTest {

    private final ColumnValidationRule rule = new ColumnAllowedValuesValidationRule("A", "I");

    @Test
    @DisplayName("Allowed values are valid")
    void validateAllowed() throws Exception {
        assertTrue(rule.validate("A"));
        assertTrue(rule.validate("I"));
    }

    @Test
    @DisplayName("Other values are invalid (case-sensitive)")
    void validateNotAllowed() {
        assertThrows(ValueNotAllowedException.class, () -> rule.validate("a"));
        assertThrows(ValueNotAllowedException.class, () -> rule.validate("X"));
        assertThrows(ValueNotAllowedException.class, () -> rule.validate(""));
    }
}
