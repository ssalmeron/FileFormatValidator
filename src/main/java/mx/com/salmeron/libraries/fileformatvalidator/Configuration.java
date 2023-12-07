package mx.com.salmeron.libraries.fileformatvalidator;

import lombok.Getter;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.FileStructure;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.line.LineValidationRule;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Configuration {
    private List<LineValidationRule> lineValidationRules = new ArrayList<>();
    private FileStructure fileStructure;

    public Configuration(List<LineValidationRule> lineValidationRules) {
        this.lineValidationRules = lineValidationRules;
    }

    public Configuration(FileStructure fileStructure) {
        this.fileStructure = fileStructure;
    }

}
