package gov.va.med.avs.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Tonté Pouncil on 2/4/15.
 */
public class WebServiceResponse implements Serializable {
    private WebServiceResponseStatus status;
    private WebServicePayload payload;

    public WebServiceResponse() {}

    public WebServiceResponse(WebServiceResponseStatus status, WebServicePayload payload) {
        this.status = status;
        this.payload = payload;
    }

    public WebServiceResponseStatus getStatus() {
        return status;
    }

    public WebServicePayload getPayload() {
        return payload;
    }
}
