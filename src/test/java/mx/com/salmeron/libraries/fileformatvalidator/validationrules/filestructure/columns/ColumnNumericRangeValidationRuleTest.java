package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidNumberException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.ValueOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Column Numeric Range Validation Test")
class ColumnNumericRangeValidationRuleTest {

    @Test
    @DisplayName("Values within range (inclusive) are valid")
    void validateWithinRange() throws Exception {
        ColumnValidationRule rule = new ColumnNumericRangeValidationRule(1, 10);
        assertTrue(rule.validate("1"));
        assertTrue(rule.validate("10"));
        assertTrue(rule.validate("5.5"));
    }

    @Test
    @DisplayName("Values outside range are invalid")
    void validateOutsideRange() {
        ColumnValidationRule rule = new ColumnNumericRangeValidationRule(new BigDecimal("0.5"), new BigDecimal("1.5"));
        assertThrows(ValueOutOfRangeException.class, () -> rule.validate("0.49"));
        assertThrows(ValueOutOfRangeException.class, () -> rule.validate("1.51"));
    }

    @Test
    @DisplayName("Non numeric values are invalid")
    void validateNotNumeric() {
        ColumnValidationRule rule = new ColumnNumericRangeValidationRule(1, 10);
        assertThrows(InvalidNumberException.class, () -> rule.validate("abc"));
    }
}
