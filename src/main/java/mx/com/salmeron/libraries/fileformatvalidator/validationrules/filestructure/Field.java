package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns.ColumnValidationRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a field in a text line.
 * A field is required by default. When a field is not required and its value is empty,
 * its validation rules are skipped.
 */
@ToString
public class Field {
    @Getter
    private final String fieldName;

    @Setter
    @Getter
    private boolean required = true;

    @Setter
    @Getter
    private List<ColumnValidationRule> validationRules = new ArrayList<>();

    public Field(String fieldName) {
        this.fieldName = fieldName;
    }

    public Field(String fieldName, boolean required) {
        this.fieldName = fieldName;
        this.required = required;
    }
}
