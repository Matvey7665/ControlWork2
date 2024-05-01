package Model.Exceptions;

import java.io.IOException;

public class IdNotFoundException extends IOException {
    public IdNotFoundException(String msg) {
        super(msg);
    }
}
