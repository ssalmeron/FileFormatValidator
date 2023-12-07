package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the structure of a file
 * @author Sergio Salmeron
 * @version 1.0
 * @since 1.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class FileStructure {
    private String fieldSeparator;
    private List<HeaderLine> headerLines = new ArrayList<>();
    private ContentLine contentLine;

    public FileStructure(List<HeaderLine> headerLines,String fieldSeparator) {
        this.headerLines = headerLines;
        this.fieldSeparator = fieldSeparator;
    }

    public FileStructure(ContentLine contentLine, String fieldSeparator) {
        this.contentLine = contentLine;
        this.fieldSeparator = fieldSeparator;
    }

    public FileStructure(List<HeaderLine> headerLines, ContentLine contentLine, String fieldSeparator) {
        this.headerLines = headerLines;
        this.contentLine = contentLine;
        this.fieldSeparator = fieldSeparator;
    }
}
