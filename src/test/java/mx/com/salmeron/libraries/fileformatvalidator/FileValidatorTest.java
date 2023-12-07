package mx.com.salmeron.libraries.fileformatvalidator;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidLengthException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.ContentLine;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.Field;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.FileStructure;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnLengthValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnRegexValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineLengthValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineValidationRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

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
        lineValidationRules.add(lengthValidation);

        FileValidator validator = new FileValidator(lineValidationRules);


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

    @Test
    @DisplayName("Validate File Structure")
    void validateFileStructure() {
        String fileName = "testingFiles/filestructure/FileStructureRegex.txt";
        Optional<URL> resource = Optional.ofNullable(this.getClass().getClassLoader().getResource(fileName));


        //Create column1 definition
        Field column1 = new Field("Column1");

        //Create the list of validation rules for Column 1
        List<ColumnValidationRule> column1ValidationRules = new ArrayList<>();

        //Create regex validation rules for Column 1
        String col1Regex = "[1234567890]"; //Any digit
        Pattern col1Pattern = Pattern.compile(col1Regex);
        ColumnValidationRule column1RegexValidationRule = new ColumnRegexValidationRule(col1Pattern);

        column1ValidationRules.add(column1RegexValidationRule);
        column1.setValidationRules(column1ValidationRules);


        // Create column 2 definition
        Field column2 = new Field("Column2");

        //Create a list of validation rules for Column 2

        List<ColumnValidationRule> column2ValidationRules = new ArrayList<>();

        //Create regex validation rules for Column 2
        String col2Regex = "[A-Za-z]"; //any letter
        Pattern col2Pattern = Pattern.compile(col2Regex);
        ColumnValidationRule column2RegexValidationRule = new ColumnRegexValidationRule(col2Pattern);

        column2ValidationRules.add(column2RegexValidationRule);
        column2.setValidationRules(column2ValidationRules);


        // Create column 3 definition
        Field column3 = new Field("Column3");

        //Create a list of validation rules for Column 3

        List<ColumnValidationRule> column3ValidationRules = new ArrayList<>();

        //Create length validation rules for Column 3


        ColumnValidationRule columnLengthValidationRule = new ColumnLengthValidationRule(1);

        column3ValidationRules.add(columnLengthValidationRule);

        column3.setValidationRules(column3ValidationRules);


        // Define the columns of the text line
        //Insert in the right position

        List<Field> fieldList = new ArrayList<>();

        fieldList.add(column1);
        fieldList.add(column2);
        fieldList.add(column3);

        ContentLine contentLine = new ContentLine(fieldList, 100);

        FileStructure testFileStructure = new FileStructure(new ArrayList<>(), contentLine, "|");


        // Create an Instance of FileValidator


        FileValidator fileValidator = new FileValidator(testFileStructure);


        if (resource.isPresent()) {
            try {
                // assertDoesNotThrow(()->fileValidator.validateFileStructure(resource.get().getPath()));
                assertTrue(fileValidator.validateFileStructure(resource.get().getPath()));
            } catch (Exception e) {
                fail();
            }
        } else {
            fail();
        }


    }

    @Test
    @DisplayName("Validate Incorrect File Structure")
    void validateIncorrectFileStructure() {
        String fileName = "testingFiles/filestructure/FileStructureRegexWrong.txt";
        Optional<URL> resource = Optional.ofNullable(this.getClass().getClassLoader().getResource(fileName));


        //Create column1 definition
        Field column1 = new Field("Column1");

        //Create the list of validation rules for Column 1
        List<ColumnValidationRule> column1ValidationRules = new ArrayList<>();

        //Create regex validation rules for Column 1
        String col1Regex = "[1234567890]"; //Any digit
        Pattern col1Pattern = Pattern.compile(col1Regex);
        ColumnValidationRule column1RegexValidationRule = new ColumnRegexValidationRule(col1Pattern);

        column1ValidationRules.add(column1RegexValidationRule);
        column1.setValidationRules(column1ValidationRules);


        // Create column 2 definition
        Field column2 = new Field("Column2");

        //Create a list of validation rules for Column 2

        List<ColumnValidationRule> column2ValidationRules = new ArrayList<>();

        //Create regex validation rules for Column 2
        String col2Regex = "[A-Za-z]"; //any letter
        Pattern col2Pattern = Pattern.compile(col2Regex);
        ColumnValidationRule column2RegexValidationRule = new ColumnRegexValidationRule(col2Pattern);

        column2ValidationRules.add(column2RegexValidationRule);
        column2.setValidationRules(column2ValidationRules);


        // Create column 3 definition
        Field column3 = new Field("Column3");

        //Create a list of validation rules for Column 3

        List<ColumnValidationRule> column3ValidationRules = new ArrayList<>();

        //Create length validation rules for Column 3


        ColumnValidationRule columnLengthValidationRule = new ColumnLengthValidationRule(1);

        column3ValidationRules.add(columnLengthValidationRule);

        column3.setValidationRules(column3ValidationRules);


        // Define the columns of the text line
        //Insert in the right position

        List<Field> fieldList = new ArrayList<>();

        fieldList.add(column1);
        fieldList.add(column2);
        fieldList.add(column3);

        ContentLine contentLine = new ContentLine(fieldList, 100);

        FileStructure testFileStructure = new FileStructure(new ArrayList<>(), contentLine, "|");


        // Create an Instance of FileValidator


        FileValidator fileValidator = new FileValidator(testFileStructure);


        if (resource.isPresent()) {

            assertThrows(InvalidLengthException.class, () -> fileValidator.validateFileStructure(resource.get().getPath()));

        } else {
            fail();
        }


    }

}