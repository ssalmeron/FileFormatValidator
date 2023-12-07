package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnValidationRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a field in a text line
 */
@ToString
public class Field {
    private final String fieldName;

    @Setter
    @Getter
    private List<ColumnValidationRule> validationRules = new ArrayList<>();

    public Field(String fieldName) {
        this.fieldName = fieldName;
    }
}
