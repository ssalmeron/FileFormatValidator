package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import lombok.AllArgsConstructor;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.InvalidLengthException;

@ToString
@AllArgsConstructor
public class ColumnLengthValidationRule implements ColumnValidationRule{

    private int columnRequiredLength;
    @Override
    public boolean validate(String text) throws Exception {

        if (text.length()!=columnRequiredLength){
            throw new InvalidLengthException(text.length(), columnRequiredLength);

        }

        return true;
    }
}
