package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Length Validation Rule Test")
class LengthValidationRuleTest {

    @Test
    @DisplayName("Validation of Length 10")
    void validateSuccessful() {

        LineLengthValidationRule validationRule = new LineLengthValidationRule(10);
        String line = "1234567890";
        assertTrue(validationRule.validate(line));

    }

     @Test
     @DisplayName("Validation of Length 31")
    void validateNotSuccessful() {

        LineLengthValidationRule validationRule = new LineLengthValidationRule(10);

        String line = "This is a line of 31 Characters";
        assertFalse(validationRule.validate(line));

    }
}