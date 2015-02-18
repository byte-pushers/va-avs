package gov.va.med.avs.exception;

/**
 * Created by Tonté Pouncil on 2/18/15.
 */
public class AfterVisitSummariesNotFoundException extends Exception {
    public AfterVisitSummariesNotFoundException() {
        super();
    }

    public AfterVisitSummariesNotFoundException(String message) {
        super(message);
    }

    public AfterVisitSummariesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AfterVisitSummariesNotFoundException(Throwable cause) {
        super(cause);
    }
}
