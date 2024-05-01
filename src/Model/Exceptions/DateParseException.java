package Model.Exceptions;

import java.text.ParseException;

public class DateParseException extends ParseException {
    public DateParseException(String s, int errorOffset) {
        super(s, errorOffset);
    }
}
