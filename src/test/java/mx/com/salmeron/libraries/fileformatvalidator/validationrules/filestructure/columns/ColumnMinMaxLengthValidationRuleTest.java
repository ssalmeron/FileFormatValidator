package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Column Min Max Length Validation Test")
class ColumnMinMaxLengthValidationRuleTest {

    private final ColumnValidationRule rule = new ColumnMinMaxLengthValidationRule(2, 4);

    @Test
    @DisplayName("Length within range (inclusive) is valid")
    void validateWithinRange() throws Exception {
        assertTrue(rule.validate("ab"));
        assertTrue(rule.validate("abc"));
        assertTrue(rule.validate("abcd"));
    }

    @Test
    @DisplayName("Length outside range is invalid")
    void validateOutsideRange() {
        assertThrows(InvalidLengthException.class, () -> rule.validate("a"));
        assertThrows(InvalidLengthException.class, () -> rule.validate("abcde"));
    }
}
