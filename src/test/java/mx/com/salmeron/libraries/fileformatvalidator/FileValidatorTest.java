package mx.com.salmeron.libraries.fileformatvalidator;

import mx.com.salmeron.libraries.fileformatvalidator.result.ValidationError;
import mx.com.salmeron.libraries.fileformatvalidator.result.ValidationResult;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidLengthException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidNumberException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.TextDoesNotMatchRegexException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.ValueNotAllowedException;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.ContentLine;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.Field;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.FileStructure;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.HeaderLine;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnAllowedValuesValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnDateValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnLengthValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnNumericValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnRegexValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineLengthValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineValidationRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.charset.StandardCharsets;
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
        String col1Regex = "[0-9]+"; //Only digits
        Pattern col1Pattern = Pattern.compile(col1Regex);
        ColumnValidationRule column1RegexValidationRule = new ColumnRegexValidationRule(col1Pattern);

        column1ValidationRules.add(column1RegexValidationRule);
        column1.setValidationRules(column1ValidationRules);


        // Create column 2 definition
        Field column2 = new Field("Column2");

        //Create a list of validation rules for Column 2

        List<ColumnValidationRule> column2ValidationRules = new ArrayList<>();

        //Create regex validation rules for Column 2
        String col2Regex = "[A-Za-z]+"; //Only letters
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
        String col1Regex = "[0-9]+"; //Only digits
        Pattern col1Pattern = Pattern.compile(col1Regex);
        ColumnValidationRule column1RegexValidationRule = new ColumnRegexValidationRule(col1Pattern);

        column1ValidationRules.add(column1RegexValidationRule);
        column1.setValidationRules(column1ValidationRules);


        // Create column 2 definition
        Field column2 = new Field("Column2");

        //Create a list of validation rules for Column 2

        List<ColumnValidationRule> column2ValidationRules = new ArrayList<>();

        //Create regex validation rules for Column 2
        String col2Regex = "[A-Za-z]+"; //Only letters
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

    /**
     * Content line: Id (digits) | Name (letters) | Status (A or I)
     */
    private ContentLine idNameStatusContentLine(int maxLines, boolean optionalNameAndStatus) {
        Field id = new Field("Id");
        id.setValidationRules(List.of(new ColumnNumericValidationRule(false)));

        Field name = new Field("Name", !optionalNameAndStatus);
        name.setValidationRules(List.of(new ColumnRegexValidationRule(Pattern.compile("[A-Za-z]+"))));

        Field status = new Field("Status", !optionalNameAndStatus);
        status.setValidationRules(List.of(new ColumnAllowedValuesValidationRule("A", "I")));

        return new ContentLine(List.of(id, name, status), maxLines);
    }

    private String resourcePath(String fileName) {
        URL resource = this.getClass().getClassLoader().getResource(fileName);
        assertNotNull(resource, "Missing test resource " + fileName);
        return resource.getPath();
    }

    @Test
    @DisplayName("Collect every error with line, column and field name")
    void validateStructureCollectsAllErrors() throws Exception {
        FileStructure fileStructure = new FileStructure(idNameStatusContentLine(0, false), "|");
        FileValidator fileValidator = new FileValidator(fileStructure);

        ValidationResult result = fileValidator.validateStructure(resourcePath("testingFiles/filestructure/MultipleErrors.txt"));

        assertFalse(result.isValid());
        assertEquals(3, result.getErrorCount());

        ValidationError idError = result.getErrors().get(0);
        assertEquals(2, idError.getLineNumber());
        assertEquals(0, idError.getColumnIndex());
        assertEquals("Id", idError.getFieldName());
        assertEquals("x", idError.getValue());
        assertInstanceOf(InvalidNumberException.class, idError.getCause());

        ValidationError nameError = result.getErrors().get(1);
        assertEquals(3, nameError.getLineNumber());
        assertEquals("Name", nameError.getFieldName());
        assertInstanceOf(TextDoesNotMatchRegexException.class, nameError.getCause());

        ValidationError statusError = result.getErrors().get(2);
        assertEquals(4, statusError.getLineNumber());
        assertEquals("Status", statusError.getFieldName());
        assertInstanceOf(ValueNotAllowedException.class, statusError.getCause());
    }

    @Test
    @DisplayName("Stop collecting errors when max errors is reached")
    void validateStructureMaxErrors() throws Exception {
        FileStructure fileStructure = new FileStructure(idNameStatusContentLine(0, false), "|");
        FileValidator fileValidator = new FileValidator(fileStructure);

        ValidationResult result = fileValidator.validateStructure(
                resourcePath("testingFiles/filestructure/MultipleErrors.txt"), StandardCharsets.UTF_8, 2);

        assertEquals(2, result.getErrorCount());
        assertTrue(result.isMaxErrorsReached());
    }

    @Test
    @DisplayName("Optional empty fields are valid")
    void validateStructureOptionalEmptyFields() throws Exception {
        FileStructure fileStructure = new FileStructure(idNameStatusContentLine(0, true), "|");
        FileValidator fileValidator = new FileValidator(fileStructure);

        ValidationResult result = fileValidator.validateStructure(resourcePath("testingFiles/filestructure/EmptyFields.txt"));

        assertTrue(result.isValid(), result.toString());
    }

    @Test
    @DisplayName("Required empty fields are reported")
    void validateStructureRequiredEmptyFields() throws Exception {
        FileStructure fileStructure = new FileStructure(idNameStatusContentLine(0, false), "|");
        FileValidator fileValidator = new FileValidator(fileStructure);

        ValidationResult result = fileValidator.validateStructure(resourcePath("testingFiles/filestructure/EmptyFields.txt"));

        assertEquals(2, result.getErrorCount());
        assertEquals("Name", result.getErrors().get(0).getFieldName());
        assertEquals("Status", result.getErrors().get(1).getFieldName());
    }

    @Test
    @DisplayName("Content lines above max lines are reported")
    void validateStructureMaxLines() throws Exception {
        FileStructure fileStructure = new FileStructure(idNameStatusContentLine(1, true), "|");
        FileValidator fileValidator = new FileValidator(fileStructure);

        ValidationResult result = fileValidator.validateStructure(resourcePath("testingFiles/filestructure/EmptyFields.txt"));

        assertEquals(1, result.getErrorCount());
        assertEquals(2, result.getErrors().get(0).getLineNumber());
        assertEquals("MaxLines", result.getErrors().get(0).getRuleName());
    }

    private FileStructure withHeaderFileStructure() {
        Field code = new Field("Code");
        Field date = new Field("Date");
        date.setValidationRules(List.of(new ColumnDateValidationRule("uuuuMMdd")));
        HeaderLine header = new HeaderLine("H", List.of(code, date));

        return new FileStructure(List.of(header), idNameStatusContentLine(0, false), "|");
    }

    @Test
    @DisplayName("Validate file with header line")
    void validateStructureWithHeader() throws Exception {
        FileValidator fileValidator = new FileValidator(withHeaderFileStructure());

        ValidationResult result = fileValidator.validateStructure(resourcePath("testingFiles/filestructure/WithHeader.txt"));

        assertTrue(result.isValid(), result.toString());
    }

    @Test
    @DisplayName("Header line with wrong code is reported")
    void validateStructureWrongHeader() throws Exception {
        FileValidator fileValidator = new FileValidator(withHeaderFileStructure());

        ValidationResult result = fileValidator.validateStructure(resourcePath("testingFiles/filestructure/WrongHeader.txt"));

        assertEquals(1, result.getErrorCount());
        assertEquals(1, result.getErrors().get(0).getLineNumber());
        assertEquals("HeaderLine", result.getErrors().get(0).getRuleName());
    }

    @Test
    @DisplayName("Line validation collects every failing line")
    void validateLinesCollectsAllErrors() throws Exception {
        FileValidator validator = new FileValidator(List.of(new LineLengthValidationRule(8)));

        ValidationResult result = validator.validateLines(resourcePath("testingFiles/filestructure/MultipleErrors.txt"));

        // Lines: "1|John|A"(8) "x|Mary|A"(8) "3|Pet3r|A"(9) "4|Ann|Z"(7) "5|Bob|A"(7)
        assertEquals(3, result.getErrorCount());
        assertEquals(3, result.getErrors().get(0).getLineNumber());
    }

    @Test
    @DisplayName("Line validation with a file structure validator does not fail")
    void validateWithFileStructureValidator() {
        FileValidator fileValidator = new FileValidator(new FileStructure(idNameStatusContentLine(0, false), "|"));

        assertDoesNotThrow(() -> fileValidator.validate(resourcePath("testingFiles/filestructure/MultipleErrors.txt")));
    }

}