package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ContentLine {
    private final int maxLines;
    private final List<Field> fields;

    public ContentLine(List<Field> fields, int maxLines) {
        this.fields = fields;
        this.maxLines = maxLines;
    }
}
