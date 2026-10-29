package mx.com.salmeron.libraries.fileformatvalidator.result;

import lombok.ToString;
import lombok.Value;

/**
 * A single validation failure found while validating a file.
 *
 * @author Sergio Salmeron
 * @see ValidationResult
 */
@Value
public class ValidationError {

    /**
     * Column index used for errors that apply to the whole line
     */
    public static final int LINE_LEVEL = -1;

    /**
     * 1-based line number of the file where the error was found
     */
    int lineNumber;

    /**
     * 0-based column index, or {@link #LINE_LEVEL} if the error applies to the whole line
     */
    int columnIndex;

    /**
     * Name of the field defined in the file structure, or null for line-level errors
     */
    String fieldName;

    /**
     * Value that failed the validation (the column text, or the whole line for line-level errors)
     */
    String value;

    /**
     * Name of the rule that failed
     */
    String ruleName;

    String message;

    /**
     * Original exception thrown by the rule, if any
     */
    @ToString.Exclude
    Exception cause;
}
