package mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors;

import java.io.IOException;

public class ErrorWhileReadingSectionException extends IOException {
    private static final long serialVersionUID = 3413427779675798354L;

    public ErrorWhileReadingSectionException() {
        super("Error while reading section");
    }
}
