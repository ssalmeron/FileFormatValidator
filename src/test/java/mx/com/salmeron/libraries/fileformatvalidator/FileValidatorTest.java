package mx.com.salmeron.libraries.fileformatvalidator;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineLengthValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineValidationRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("File Validator Tests")
class FileValidatorTest {


    @Test
    @DisplayName("Validate All lines are 10 length")
    void validateAllLines10() {

        //Test File
        String fileName = "testingFiles/AllLines10.txt";
        Optional<URL> resource = Optional.ofNullable(this.getClass().getClassLoader().getResource(fileName));


        //Validation Rules
        List<LineValidationRule> lineValidationRules = new ArrayList<>();
        LineValidationRule lengthValidation = new LineLengthValidationRule(10);
        FileValidator validator = new FileValidator(lineValidationRules);




        lineValidationRules.add(lengthValidation);
        if (resource.isPresent()) {
            try {
                assertTrue(validator.validate(resource.get().getPath()));
            } catch (Exception e) {
                fail();
            }
        } else {
            fail();
        }


    }
}