package gov.va.med.avs.service;

import gov.va.med.avs.model.AfterVisitSummary;
import gov.va.med.avs.model.AfterVisitSummarySearchRequest;

/**
 * Created by Tonté Pouncil on 2/12/15.
 */
public interface AfterVisitSummaryService {
    AfterVisitSummary findAfterVisitSummaries(AfterVisitSummarySearchRequest afterVisitSummarySearchRequest);

    AfterVisitSummary getAfterVisitSummary(String clientId, String veteranId);
}
