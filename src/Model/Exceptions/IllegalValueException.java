package Model.Exceptions;

import java.io.IOException;

public class IllegalValueException extends IOException {
    public IllegalValueException(String msg) {
        super(msg);
    }
}
