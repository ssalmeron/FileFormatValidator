package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Represents a header line of a file. A header line must start with the {@link #lineCode}
 * and its fields are validated against the {@link #fields} definition.
 */
@Getter
@ToString
@AllArgsConstructor
public class HeaderLine {
    private String lineCode;
    private List<Field> fields;
}
