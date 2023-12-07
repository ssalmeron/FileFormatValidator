package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
public class HeaderLine {
    private String lineCode;
    private List<Field> fields;
}
