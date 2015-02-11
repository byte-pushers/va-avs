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

    @RequestMapping(value = "/afterVisitSummaries", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public WebServiceResponse getAfterVisitSummariesAsJSON(@RequestBody AfterVisitSummarySearchRequest avsSearchRequest) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();
        afterVisitSummaries.add(new AfterVisitSummary("0", "1234", "After Visit Summary", "After Visit Summary", new Date()));

        WebServicePayload payload = new WebServicePayload(afterVisitSummaries);

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Successful), payload);
    }

    /*@RequestMapping(value = "/afterVisitSummary", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public WebServiceResponse getAfterVisitSummariesAsXML(@RequestBody AfterVisitSummarySearchRequest avsSearchRequest) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();
        afterVisitSummaries.add(new AfterVisitSummary("0", "1234", "After Visit Summary", "After Visit Summary", new Date()));

        WebServicePayload payload = new WebServicePayload(afterVisitSummaries);

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Successful), payload);
    }*/
}
