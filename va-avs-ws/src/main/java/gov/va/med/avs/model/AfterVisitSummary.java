package gov.va.med.avs.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.va.med.avs.serializer.JsonDateSerializer;

import java.util.Date;

/**
 * Created by Tonté Pouncil on 2/4/15.
 */
public class AfterVisitSummary{
    private String id;
    private String veteranId;
    private String name;
    private String description;
    private Date createdDate;
    private byte[] base64EncodedPDF;

    public AfterVisitSummary() {

    }
    public AfterVisitSummary(String id, String veteranId, String name, String description, Date createdDate) {
        this.id = id;
        this.veteranId = veteranId;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
    }

    public AfterVisitSummary(String id, String veteranId, String name, String description, Date createdDate, byte[] base64EncodedPDF) {
        this.id = id;
        this.veteranId = veteranId;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.base64EncodedPDF = base64EncodedPDF;
    }

    public String getId() {
        return id;
    }

    public String getVeteranId() {
        return veteranId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getCreatedDate() {
        return createdDate;
    }

    public byte[] getBase64EncodedPDF() {
        return base64EncodedPDF;
    }
}
