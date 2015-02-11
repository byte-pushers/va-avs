package gov.va.med.avs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tonté Pouncil on 2/6/15.
 */
public class WebServicePayload {
    private List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();

    public WebServicePayload() {}

    public WebServicePayload(List<AfterVisitSummary> afterVisitSummaries) {
        this.afterVisitSummaries = afterVisitSummaries;
    }

    public List<AfterVisitSummary> getAfterVisitSummaries() {
        return afterVisitSummaries;
    }

    public void setAfterVisitSummaries(List<AfterVisitSummary> afterVisitSummaries) {
        this.afterVisitSummaries = afterVisitSummaries;
    }
}
