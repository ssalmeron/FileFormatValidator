package mx.com.salmeron.libraries.fileformatvalidator.validationrules.columns;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class ColumnLengthValidationRule implements ColumnValidationRule{

    private int length;

    @Override
    public boolean validate(String text) throws Exception {
        return text.trim().length()==length;
    }
}
