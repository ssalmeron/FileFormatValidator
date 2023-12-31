package mx.com.salmeron.libraries.fileformatvalidator.validationrules.columns;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidLengthException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnLengthValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnValidationRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Column - Length Validation Rule Tests")
class ColumnLengthValidationRuleTest {

    @Test
    @DisplayName("Column - Length Validation Rule 5 Length")
    void validateColumnLength() {

        ColumnValidationRule columnLength5 = new ColumnLengthValidationRule(5);

        try {
            assertTrue(columnLength5.validate("12345"));
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    @DisplayName("Column - Length Validation Rule Incorrect Length")
    void validateColumnLengthWrong() {

        ColumnValidationRule columnLength5 = new ColumnLengthValidationRule(10);

        try {
            assertThrows(InvalidLengthException.class, ()->columnLength5.validate("12345"));
        } catch (Exception e) {
            fail();
        }


    }
}