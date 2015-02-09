package gov.va.med.avs.model;

import java.util.List;

/**
 * Created by Tonté Pouncil on 2/4/15.
 */
public class WebServiceResponseStatus {
    public enum Request {
        Successful("Successful"),
        Failure("Failure");

        private final String value;

        public String getValue() {
            return value;
        }

        Request(String value) {
            this.value = value;
        }
    }

    private List<WebServiceResponseMessage> messages;
    private Request requestStatus;

    public WebServiceResponseStatus() {}

    public WebServiceResponseStatus(Request requestStatus) {
        this.requestStatus = requestStatus;
    }

    public WebServiceResponseStatus(Request requestStatus, List<WebServiceResponseMessage> messages){
        this.messages = messages;
        this.requestStatus = requestStatus;
    }

    public List<WebServiceResponseMessage> getMessages() {
        return messages;
    }

    public Request getRequestStatus(){
        return requestStatus;
    }
}
