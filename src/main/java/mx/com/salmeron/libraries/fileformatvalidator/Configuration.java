package mx.com.salmeron.libraries.fileformatvalidator;

import mx.com.salmeron.libraries.fileformatvalidator.validationrules.columns.ColumnValidationRule;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineValidationRule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Configuration {
    private List<LineValidationRule> lineValidationRules = new ArrayList<>();
    private List<ColumnValidationRule> columnValidationRules = new ArrayList<>();


}
