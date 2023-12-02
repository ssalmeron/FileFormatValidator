package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class LineNumberOfColumnsValidationRule implements LineValidationRule{
    @Override
    public boolean validate(String text) throws Exception {
        return false;
    }
}
