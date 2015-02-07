package gov.va.med.avs.controller;

import gov.va.med.avs.model.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class AfterVisitSummaryController {

    @RequestMapping(value = "/afterVisitSummaries", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public WebServiceResponse getAfterVisitSummaries(@RequestBody AfterVisitSummarySearchRequest avsSearchRequest) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();
        afterVisitSummaries.add(new AfterVisitSummary("0", "1234", "After Visit Summary", "After Visit Summary", new Date()));

        WebServicePayload payload = new WebServicePayload(afterVisitSummaries);

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Successful), payload);
    }
}
