package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Column Numeric Validation Test")
class ColumnNumericValidationRuleTest {

    @Test
    @DisplayName("Integers are valid")
    void validateIntegers() throws Exception {
        ColumnValidationRule rule = new ColumnNumericValidationRule(false);
        assertTrue(rule.validate("123"));
        assertTrue(rule.validate("-5"));
        assertTrue(rule.validate("+007"));
    }

    @Test
    @DisplayName("Decimals are invalid when not allowed")
    void validateDecimalsNotAllowed() {
        ColumnValidationRule rule = new ColumnNumericValidationRule(false);
        assertThrows(InvalidNumberException.class, () -> rule.validate("1.5"));
        assertThrows(InvalidNumberException.class, () -> rule.validate("12a"));
        assertThrows(InvalidNumberException.class, () -> rule.validate(""));
    }

    @Test
    @DisplayName("Decimals are valid when allowed")
    void validateDecimalsAllowed() throws Exception {
        ColumnValidationRule rule = new ColumnNumericValidationRule(true);
        assertTrue(rule.validate("1.5"));
        assertTrue(rule.validate("-0.25"));
        assertTrue(rule.validate("10"));
        assertThrows(InvalidNumberException.class, () -> rule.validate("1.2.3"));
        assertThrows(InvalidNumberException.class, () -> rule.validate("."));
    }
}
