package gov.va.med.avs.exception;

/**
 * Created by Tonté Pouncil on 2/18/15.
 */
public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException() {
        super();
    }

    public InvalidDateFormatException(String message) {
        super(message);
    }

    public InvalidDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateFormatException(Throwable cause) {
        super(cause);
    }
}
