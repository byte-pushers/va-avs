package gov.va.med.avs.service;

import gov.va.med.avs.model.AfterVisitSummary;
import gov.va.med.avs.model.AfterVisitSummarySearchRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Tonté Pouncil on 2/12/15.
 */
@Service
public class AfterVisitSummaryMockService implements AfterVisitSummaryService {
    public AfterVisitSummaryMockService() {
        super();
    }

    @Override
    public AfterVisitSummary findAfterVisitSummaries(AfterVisitSummarySearchRequest afterVisitSummarySearchRequest) {
        return new AfterVisitSummary("0", "1234", "After Visit Summary", "After Visit Summary", new Date());
    }

    @Override
    public AfterVisitSummary getAfterVisitSummary(String clientId, String veteranId) {
        return new AfterVisitSummary("0", "1234", "After Visit Summary", "After Visit Summary", new Date(), getPDF());
    }

    public byte[] getPDF() {
        Resource pdf = new ClassPathResource("avs-test.pdf");
        byte[] pdfBytes = new byte[0];
        try{
            pdfBytes = IOUtils.toByteArray(pdf.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfBytes;
    }
}
