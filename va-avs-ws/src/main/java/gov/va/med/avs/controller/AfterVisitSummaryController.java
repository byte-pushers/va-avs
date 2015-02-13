package gov.va.med.avs.controller;

import gov.va.med.avs.model.*;
import gov.va.med.avs.service.AfterVisitSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AfterVisitSummaryController {
    @Autowired
    private AfterVisitSummaryService afterVisitSummaryService;

    @RequestMapping(value = "/afterVisitSummaries", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public WebServiceResponse getAfterVisitSummaries(@RequestBody AfterVisitSummarySearchRequest afterVisitSummarySearchRequest) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();
        afterVisitSummaries.add(getAfterVisitSummaryService().findAfterVisitSummaries(afterVisitSummarySearchRequest));

        WebServicePayload payload = new WebServicePayload(afterVisitSummaries);

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Successful, new ArrayList<String>()), payload);
    }

    @RequestMapping(value = "/afterVisitSummaries/{afterVisitSummaryId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public WebServiceResponse getAfterVisitSummary(@PathVariable String afterVisitSummaryId, @RequestParam("clientId") String clientId, @RequestParam("veteranId") String veteranId) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();
        afterVisitSummaries.add(getAfterVisitSummaryService().getAfterVisitSummary(clientId, veteranId));

        WebServicePayload payload = new WebServicePayload(afterVisitSummaries);

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Successful, new ArrayList<String>()), payload);
    }

    public AfterVisitSummaryService getAfterVisitSummaryService() {
        return afterVisitSummaryService;
    }

    public void setAfterVisitSummaryService(AfterVisitSummaryService afterVisitSummaryService) {
        this.afterVisitSummaryService = afterVisitSummaryService;
    }
}
