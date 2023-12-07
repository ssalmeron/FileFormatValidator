package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Content Line Test")
class ContentLineTest {

    @Test
    @DisplayName("Content Line Test")
    void testContentLine() {

        //Create the definition of a line
        Field fieldName = new Field("Name");
        Field fieldLastName = new Field("LastName");
        Field fieldEmail = new Field("Email");

        List<Field> contentFields = List.of(fieldName,fieldLastName,fieldEmail);
        ContentLine contentLine = new ContentLine(contentFields, 100);
        assertEquals(3,contentLine.getFields().size());



    }


}