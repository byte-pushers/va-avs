package gov.va.med.avs.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.med.avs.serializer.JsonDateDeserializer;

import java.util.Date;

/**
 * Created by Tonté Pouncil on 2/4/15.
 */
public class AfterVisitSummarySearchCriteria {
    private String clientId = null;
    private String veteranId = null;
    private Date startDate = null;
    private Date endDate = null;

    public AfterVisitSummarySearchCriteria() {}

    public AfterVisitSummarySearchCriteria(String clientId, String veteranId, Date startDate, Date endDate) {
        this.clientId = clientId;
        this.veteranId = veteranId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getVeteranId() {
        return veteranId;
    }

    public void setVeteranId(String veteranId) {
        this.veteranId = veteranId;
    }

    @JsonDeserialize(using=JsonDateDeserializer.class)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonDeserialize(using=JsonDateDeserializer.class)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
