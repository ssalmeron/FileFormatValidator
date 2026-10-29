package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidDateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Column Date Validation Test")
class ColumnDateValidationRuleTest {

    @Test
    @DisplayName("Valid dates")
    void validateValidDates() throws Exception {
        assertTrue(new ColumnDateValidationRule("uuuuMMdd").validate("20240229"));
        assertTrue(new ColumnDateValidationRule("dd/MM/uuuu").validate("31/12/2023"));
        assertTrue(new ColumnDateValidationRule("uuuu-MM-dd HH:mm:ss").validate("2024-01-15 23:59:59"));
    }

    @Test
    @DisplayName("Invalid dates")
    void validateInvalidDates() {
        ColumnValidationRule rule = new ColumnDateValidationRule("uuuuMMdd");
        assertThrows(InvalidDateException.class, () -> rule.validate("20230229")); // not a leap year
        assertThrows(InvalidDateException.class, () -> rule.validate("20241301"));
        assertThrows(InvalidDateException.class, () -> rule.validate("2024-01-15"));
        assertThrows(InvalidDateException.class, () -> rule.validate(""));
    }
}
