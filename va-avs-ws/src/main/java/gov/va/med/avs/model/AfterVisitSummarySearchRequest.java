package gov.va.med.avs.model;

import java.io.Serializable;

/**
 * Created by Tonté Pouncil on 2/5/15.
 */

public class AfterVisitSummarySearchRequest {

    private AfterVisitSummarySearchCriteria afterVisitSummarySearchCriteria;

    public AfterVisitSummarySearchRequest() {}

    public AfterVisitSummarySearchRequest(AfterVisitSummarySearchCriteria afterVisitSummarySearchCriteria) {
        this.afterVisitSummarySearchCriteria = afterVisitSummarySearchCriteria;
    }

    public AfterVisitSummarySearchCriteria getAfterVisitSummarySearchCriteria() {
        return afterVisitSummarySearchCriteria;
    }

    public void setAfterVisitSummarySearchCriteria(AfterVisitSummarySearchCriteria afterVisitSummarySearchCriteria) {
        this.afterVisitSummarySearchCriteria = afterVisitSummarySearchCriteria;
    }
}
