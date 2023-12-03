package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Line - Number of columns Validation Rule Tests")
class LineNumberOfColumnsValidationRuleTest {

    @Test
    @DisplayName("Number of columns validation 5 columns with ^")
    void validate5Columns() {
        LineValidationRule validationRule = new LineNumberOfColumnsValidationRule(5,"^");
        String line = "Column1^Column2^Column3^Column4^Column5";

        try {
            assertTrue(validationRule.validate(line));
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    @DisplayName("Number of columns validation 5 columns with ^ - Wrong")
    void validate5ColumnsWrong() {
        LineValidationRule validationRule = new LineNumberOfColumnsValidationRule(5,"^");
        String line = "Column1^Column2^Column3^Column4^Column5^Column6";

        try {
            assertFalse(validationRule.validate(line));
        } catch (Exception e) {
            fail();
        }


    }
}