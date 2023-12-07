package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidFieldSeparatorLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Line - Field Separator Validation Rule Tests")
class FieldSeparatorValidationRuleTest {

    @Test
    @DisplayName("Validate Field Separator Incorrect length")
    void validateFieldSeparatorIncorrectLength() {
        LineFieldSeparatorValidationRule validationRule = new LineFieldSeparatorValidationRule(",,"); //2 commas
        String line = "1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567";
        assertThrows(InvalidFieldSeparatorLengthException.class,()->validationRule.validate(line));

    }

    @Test
    @DisplayName("Validate Field Separator Correct length")
    void validateFieldSeparatorCorrectLength() {
        LineFieldSeparatorValidationRule validationRule = new LineFieldSeparatorValidationRule(","); //2 commas
        String line = "1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567";
        assertDoesNotThrow(()->validationRule.validate(line));

    }




    @Test
    @DisplayName("Validate Correct Separator |")
    void validateCorrectSeparator() {
         LineFieldSeparatorValidationRule validationRule = new LineFieldSeparatorValidationRule(","); //2 commas
        String line = "1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567";
        try {
            assertTrue(validationRule.validate(line));
        } catch (InvalidFieldSeparatorLengthException e) {
            fail();
        }


    }

    @Test
    @DisplayName("Validate Incorrect Separator")
    void validateIncorrectSeparator() {
        LineFieldSeparatorValidationRule pipeSeparatorValidationRule = new LineFieldSeparatorValidationRule("|"); //2 commas
        String line = "1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567890,1234567";
        try {
            assertFalse(pipeSeparatorValidationRule.validate(line));
        } catch (InvalidFieldSeparatorLengthException e) {
            fail();
        }
    }

}