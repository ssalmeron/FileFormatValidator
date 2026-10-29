package mx.com.salmeron.libraries.fileformatvalidator.result;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Result of validating a file. Contains every {@link ValidationError} found,
 * up to {@link #getMaxErrors()} errors (0 means unlimited).
 *
 * @author Sergio Salmeron
 * @see ValidationError
 */
@ToString
public class ValidationResult {

    public static final int UNLIMITED_ERRORS = 0;

    private final List<ValidationError> errors = new ArrayList<>();

    @Getter
    private final int maxErrors;

    public ValidationResult() {
        this(UNLIMITED_ERRORS);
    }

    public ValidationResult(int maxErrors) {
        this.maxErrors = maxErrors;
    }

    /**
     * Adds an error, unless the maximum number of errors has already been reached
     *
     * @param error Error found during the validation
     */
    public void addError(ValidationError error) {
        if (!isMaxErrorsReached()) {
            errors.add(error);
        }
    }

    /**
     * @return True if no errors were found
     */
    public boolean isValid() {
        return errors.isEmpty();
    }

    /**
     * @return True if the maximum number of errors was reached and the validation should stop
     */
    public boolean isMaxErrorsReached() {
        return maxErrors > UNLIMITED_ERRORS && errors.size() >= maxErrors;
    }

    public List<ValidationError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public int getErrorCount() {
        return errors.size();
    }
}
