package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.errors;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineValidationRule;
import lombok.AllArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
public class LineNumberOfColumsValidationRule implements LineValidationRule {
    @Override
    public boolean validate(String text) throws Exception {

        return false;



    }
}
