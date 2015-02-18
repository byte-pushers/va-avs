package gov.va.med.avs.controller;

import gov.va.med.avs.exception.*;
import gov.va.med.avs.model.*;
import gov.va.med.avs.service.AfterVisitSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @ExceptionHandler(VeteranNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public WebServiceResponse handleException(VeteranNotFoundException e) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Failure, new ArrayList<String>()));
    }

    @ExceptionHandler(AfterVisitSummariesNotFoundException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public WebServiceResponse handleException(AfterVisitSummariesNotFoundException e) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Successful, new ArrayList<String>()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public WebServiceResponse handleException(IllegalArgumentException e) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Failure, new ArrayList<String>()));
    }

    @ExceptionHandler(RequestedResourceTooLargeException.class)
    @ResponseStatus(HttpStatus.REQUEST_ENTITY_TOO_LARGE)
    @ResponseBody
    public WebServiceResponse handleException(RequestedResourceTooLargeException e) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Failure, new ArrayList<String>()));
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public WebServiceResponse handleException(InvalidDateRangeException e) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Failure, new ArrayList<String>()));
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public WebServiceResponse handleException(InvalidDateFormatException e) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Failure, new ArrayList<String>()));
    }

    @ExceptionHandler(AfterVisitSummaryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public WebServiceResponse handleException(AfterVisitSummaryNotFoundException e) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Failure, new ArrayList<String>()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public WebServiceResponse handleException(Exception e) {
        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();

        return new WebServiceResponse(new WebServiceResponseStatus(WebServiceResponseStatus.Request.Failure, new ArrayList<String>()));
    }

    public AfterVisitSummaryService getAfterVisitSummaryService() {
        return afterVisitSummaryService;
    }

    public void setAfterVisitSummaryService(AfterVisitSummaryService afterVisitSummaryService) {
        this.afterVisitSummaryService = afterVisitSummaryService;
    }
}
