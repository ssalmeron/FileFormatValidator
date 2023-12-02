package mx.com.salmeron.libraries.fileformatvalidator.validationrules.line;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class LineLengthValidationRule implements LineValidationRule {

    private int lineLength;
    @Override
    public boolean validate(String text) {
        return text.length()==lineLength;
    }
}
