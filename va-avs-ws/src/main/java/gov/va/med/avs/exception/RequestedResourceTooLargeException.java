package gov.va.med.avs.exception;

/**
 * Created by Tonté Pouncil on 2/18/15.
 */
public class RequestedResourceTooLargeException extends Exception {
    public RequestedResourceTooLargeException() {
        super();
    }

    public RequestedResourceTooLargeException(String message) {
        super(message);
    }

    public RequestedResourceTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestedResourceTooLargeException(Throwable cause) {
        super(cause);
    }
}
