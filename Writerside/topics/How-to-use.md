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
        <p>Validate the file by passing the filePath to the validate method</p>
        <code-block lang="java">
            validator.validate("fileToValidate.txt")
        </code-block>
    </step>
</procedure>

> **Important information**
>
> The validate method will return true if all lines of the file comply with the validation rules added to the validator.
>
{style="warning"}

