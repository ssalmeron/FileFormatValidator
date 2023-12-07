package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.TextDoesNotMatchRegexException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Column - Regex Validation Rule")
class ColumnRegexValidationRuleTest {

    @Test
    void validateStrinRegexAllDigits() throws Exception {


        Pattern regex = Pattern.compile("[1234567890]");

        String textToBeValidated = "143534548";

        ColumnValidationRule columnValidationRule =  new ColumnRegexValidationRule(regex);

        assertTrue(columnValidationRule.validate(textToBeValidated));




    }


    @Test
    void validateIncorrectStringRegex() {

        Pattern regex = Pattern.compile("^[0-9]*$");

        String textToBeValidated = "1435ab4548";

        ColumnValidationRule columnValidationRule =  new ColumnRegexValidationRule(regex);

        assertThrows(TextDoesNotMatchRegexException.class,()-> columnValidationRule.validate(textToBeValidated));

    }
}