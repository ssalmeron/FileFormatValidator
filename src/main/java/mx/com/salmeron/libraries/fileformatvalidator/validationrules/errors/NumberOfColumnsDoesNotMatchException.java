package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

public class NumberOfColumnsDoesNotMatchException extends Exception {
    private static final long serialVersionUID = 1889882939242116451L;

    public NumberOfColumnsDoesNotMatchException(int numberOfFields, int tokenizedStringNumColumns) {
        super("The number of columns from the file (" + numberOfFields + ") does not match with the number of columns defined in the file structure (" + tokenizedStringNumColumns + ").");

    }
}
