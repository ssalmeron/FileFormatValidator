package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Column Length Validation Test")
class ColumnLengthValidationRuleTest {

    @Test
    @DisplayName("Column Length Validation Test")
    void validateColumnLength() throws Exception {

        ColumnValidationRule columnLengthValidationRule = new ColumnLengthValidationRule(30);

        String columnText = "ded874j5mkjh1234dcfrtgbhy09845";



        assertDoesNotThrow(()->columnLengthValidationRule.validate(columnText));

        assertTrue(columnLengthValidationRule.validate(columnText));




    }


    @Test
    @DisplayName("Invalid Length Exception Test")
    void validateIncorrectLengthException() {

        ColumnValidationRule columnLengthValidationRule = new ColumnLengthValidationRule(30);

        String columnText = "ded874j5mkjh";

        assertThrows(InvalidLengthException.class, ()->columnLengthValidationRule.validate(columnText));
    }
}