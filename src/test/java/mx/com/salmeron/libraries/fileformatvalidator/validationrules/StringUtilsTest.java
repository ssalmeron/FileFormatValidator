package mx.com.salmeron.libraries.fileformatvalidator.validationrules;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("String Utils tests")
class StringUtilsTest {

    @Test
    @DisplayName("Tokenize string test with comma")
    void tokenizeStringComa() {

        String[] tokenizedString = StringUtils.tokenizeString("Hello,World!", ",");
        assertEquals(2, tokenizedString.length);


    }

    @Test
    @DisplayName("Tokenize string test with Pipe")
    void tokenizeStringPipe() {

        String[] tokenizedString = StringUtils.tokenizeString("Hello|World|!", "|");
        assertEquals(3, tokenizedString.length);


    }


    @Test
    @DisplayName("Tokenize string wrong separator")
    void tokenizeStringWrongSeparator() {

        String[] tokenizedString = StringUtils.tokenizeString("Hello|World|!", "^");
        assertNotEquals(3, tokenizedString.length);


    }

    @Test
    @DisplayName("Tokenize string keeps empty fields")
    void tokenizeStringEmptyFields() {

        assertArrayEquals(new String[]{"a", "", "c"}, StringUtils.tokenizeString("a||c", "|"));
        assertArrayEquals(new String[]{"a", "b", ""}, StringUtils.tokenizeString("a|b|", "|"));
        assertArrayEquals(new String[]{"", "b"}, StringUtils.tokenizeString("|b", "|"));
    }

    @Test
    @DisplayName("Tokenize string with multi character separator")
    void tokenizeStringMultiCharSeparator() {

        assertArrayEquals(new String[]{"a", "b|c"}, StringUtils.tokenizeString("a||b|c", "||"));
    }
}