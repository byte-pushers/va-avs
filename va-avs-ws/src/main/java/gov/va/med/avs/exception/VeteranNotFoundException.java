package gov.va.med.avs.exception;

/**
 * Created by Tonté Pouncil on 2/13/15.
 */
public class VeteranNotFoundException extends Exception{

    public VeteranNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VeteranNotFoundException(Throwable cause) {
        super(cause);
    }

    public VeteranNotFoundException(String message) {
        super(message);
    }

    public VeteranNotFoundException() {
        super();
    }
}
