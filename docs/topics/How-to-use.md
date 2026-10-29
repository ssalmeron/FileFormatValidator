# How to use

Make sure that:

- File format validator artifacts are in your project

<procedure title="Creation of a Format Validator Instance" id="create-instance">
    <step>
        <p>Create a List to hold all rules to be added to the Format Validator Object</p>
             <code-block lang="java">
               <![CDATA[List<LineValidationRule> lineValidationRules = new ArrayList<>();]]>
            </code-block>
    </step>
    <step>
        <p>Start defining validation rules and add them to the list</p>
        <p>Line validation rules</p>
            <ul>
                <li>
                    <control>Existing validation rule</control> for Line length. In this example it is being create a rule to validate the length of the line is equal to 10
                    <code-block lang="java">
                        LineValidationRule lengthValidation = new LineLengthValidationRule(10);
                        lineValidationRules.add(lengthValidation);
                    </code-block>
                </li>
                <li>
                    <control>Existing validation rule</control> to verify is the text line has a certain field separator. In this example the rule is being defined to verify for pipe "|" separator
                    <code-block lang="java">
                        LineFieldSeparatorValidationRule pipeSeparatorValidationRule = new LineFieldSeparatorValidationRule("|");
                        lineValidationRules.add(pipeSeparatorValidationRule);
                    </code-block>
                </li>
            </ul>      
    </step>
    <step>
        <p>Create the FileValidator by passing the list of validation rules to constructor</p>
        <code-block lang="java">
            FileValidator validator = new FileValidator(lineValidationRules);
        </code-block>
    </step>
    <step>
        <p>Validate the file by passing the filePath to the validateLines method</p>
        <code-block lang="java">
            ValidationResult result = validator.validateLines("fileToValidate.txt");
        </code-block>
    </step>
</procedure>

> **Important information**
>
> `validateLines` checks every line and returns a `ValidationResult` with all the errors found.
> The older `validate` method is deprecated: it stops at the first error and returns `false` (or throws the rule's exception).
>
{style="warning"}

## Validate a file field by field

Define the structure of the file: the field separator, optional header lines and the content line with its fields.
Each field has a list of column validation rules.

```java
Field id = new Field("Id");
id.setValidationRules(List.of(new ColumnRequiredValidationRule(), new ColumnNumericValidationRule(false)));

Field name = new Field("Name", false); // optional: rules are skipped when the value is empty
name.setValidationRules(List.of(new ColumnMinMaxLengthValidationRule(1, 30)));

Field status = new Field("Status");
status.setValidationRules(List.of(new ColumnAllowedValuesValidationRule("A", "I")));

Field date = new Field("Date");
date.setValidationRules(List.of(new ColumnDateValidationRule("uuuuMMdd")));

// The first line must start with "H" and have 2 fields: H|20240115
HeaderLine header = new HeaderLine("H", List.of(new Field("Code"), date));

// Content lines: Id|Name|Status. maxLines = 0 means unlimited
ContentLine content = new ContentLine(List.of(id, name, status), 0);

FileStructure fileStructure = new FileStructure(List.of(header), content, "|");

ValidationResult result = new FileValidator(fileStructure).validateStructure("fileToValidate.txt");

if (!result.isValid()) {
    for (ValidationError error : result.getErrors()) {
        System.out.println("Line " + error.getLineNumber() + ", field " + error.getFieldName()
                + " (" + error.getValue() + "): " + error.getMessage());
    }
}
```

Use `validateStructure(path, charset, maxErrors)` to read a file with a charset other than UTF-8,
or to stop after a number of errors on very large files.

### Column validation rules

| Rule | Validates |
|---|---|
| `ColumnRequiredValidationRule()` | The value is not empty or blank |
| `ColumnLengthValidationRule(length)` | The value has exactly `length` characters |
| `ColumnMinMaxLengthValidationRule(min, max)` | The length is between `min` and `max` (inclusive) |
| `ColumnRegexValidationRule(pattern)` | The **whole** value matches the regular expression |
| `ColumnNumericValidationRule(allowDecimals)` | The value is an integer, or a decimal number if `allowDecimals` is true |
| `ColumnNumericRangeValidationRule(min, max)` | The value is a number between `min` and `max` (inclusive) |
| `ColumnDateValidationRule(pattern)` | The value is a valid date for the pattern, e.g. `uuuuMMdd` (strict: `20230229` is invalid) |
| `ColumnAllowedValuesValidationRule(values...)` | The value is one of the allowed values (case-sensitive) |

### What is reported

- A line with a different number of columns than the fields defined (empty fields count, e.g. `a||c` has 3 columns)
- A header line missing, or not starting with its line code
- Content lines beyond `maxLines`
- Every column that fails a rule, with the line number, column index, field name and value

