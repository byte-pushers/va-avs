package gov.va.med.avs.exception;

/**
 * Created by Tonté Pouncil on 2/13/15.
 */
public class AfterVisitSummaryNotFoundException extends Exception {
    public AfterVisitSummaryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AfterVisitSummaryNotFoundException(Throwable cause) {
        super(cause);
    }

    public AfterVisitSummaryNotFoundException(String message) {
        super(message);
    }

    public AfterVisitSummaryNotFoundException() {
        super();
    }
}
