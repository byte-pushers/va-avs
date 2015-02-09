package gov.va.med.avs.controller;

import com.sun.xml.bind.api.JAXBRIContext;
import gov.va.med.avs.model.*;
import gov.va.med.avs.model.jaxb.adapter.DateAdapter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.jboss.jaxb.intros.IntroductionsAnnotationReader;
import org.jboss.jaxb.intros.IntroductionsConfigParser;
import org.jboss.jaxb.intros.configmodel.JaxbIntros;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: pouncilt
 * Date: 11/5/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/jaxb2-marshaller-context.xml"})
public class MapToXMLTest {
    @Autowired
    @Qualifier("jaxb2Marshaller")
    Jaxb2Marshaller jaxb2Marshaller;

    @Test
    public void testMarshallingJaxbIntroductionMappingsToXMLForWebServiceResponse() throws Exception {
        Date todaysDate = new Date();
        String todaysDateAsString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(todaysDate);
        byte[] pdfData = getPdf("avs-test.pdf");
        String base64EncodedPdfData = Base64.encodeBase64String(pdfData);
        String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><WebServiceResponse><status><messages><message>AVS Found.</message></messages><requestStatus>Successful</requestStatus></status><payload><afterVisitSummaries><afterVisitSummary><id>1</id><veteranId>54321</veteranId><name>Pouncil AVS</name><description>AVS for Mr. Pouncil.</description><createdDate>" + todaysDateAsString + "</createdDate><base64EncodedPDF>"+ base64EncodedPdfData +"</base64EncodedPDF></afterVisitSummary></afterVisitSummaries></payload></WebServiceResponse>";
        StringWriter sw = new StringWriter();

        JaxbIntros config = IntroductionsConfigParser.parseConfig(new ClassPathResource("spring/jaxb-intros-marshaller-mapping.xml").getInputStream());
        IntroductionsAnnotationReader reader = new IntroductionsAnnotationReader(config);
        Map<String, Object> jaxbConfig = new HashMap<String, Object>();

        //jaxbConfig.put(JAXBRIContext.DEFAULT_NAMESPACE_REMAP, "http://localhost:8080/va-avs-ws/schemas");
        jaxbConfig.put(JAXBRIContext.ANNOTATION_READER, reader);
        JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {WebServiceResponse.class}, jaxbConfig);
        jaxb2Marshaller.marshal(getWebServiceResponse(todaysDate), new StreamResult(sw));

        assertEquals(expectedXML, sw.toString());
    }

    @Test
    public void testUnmarshallingXMLToJaxbIntroductionMappingsForWebServiceResponse() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date todaysDate = new Date();
        String todaysDateAsString = dateFormat.format(todaysDate);
        byte[] expectedPdfData = getPdf("avs-test.pdf");
        String base64EncodedPdfData = Base64.encodeBase64String(expectedPdfData);
        String actualXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><WebServiceResponse><status><messages><message>AVS Found.</message></messages><requestStatus>Successful</requestStatus></status><payload><afterVisitSummaries><afterVisitSummary><id>1</id><veteranId>54321</veteranId><name>Pouncil AVS</name><description>AVS for Mr. Pouncil.</description><createdDate>" + todaysDateAsString + "</createdDate><base64EncodedPDF>"+base64EncodedPdfData+"</base64EncodedPDF></afterVisitSummary></afterVisitSummaries></payload></WebServiceResponse>";
        StringReader reader = new StringReader(actualXML);
        WebServiceResponse response = (WebServiceResponse) jaxb2Marshaller.unmarshal(new StreamSource(reader));

        assertNotNull("null Response", response);
        assertNotNull("null Response Status", response.getStatus());
        WebServicePayload payload = response.getPayload();
        assertNotNull("null Response WebServicePayload", payload);
        List<AfterVisitSummary> afterVisitSummaries = payload.getAfterVisitSummaries();
        assertNotNull("null After Visit Summaries", afterVisitSummaries);
        assertTrue("no After Visit Summaries", afterVisitSummaries.size() > 0);
        assertEquals("1", "1", afterVisitSummaries.get(0).getId());
        assertEquals("54321", "54321", afterVisitSummaries.get(0).getVeteranId());
        assertEquals("Pouncil AVS", "Pouncil AVS", afterVisitSummaries.get(0).getName());
        assertEquals("AVS for Mr. Pouncil.", "AVS for Mr. Pouncil.", afterVisitSummaries.get(0).getDescription());
        assertEquals(todaysDateAsString, dateFormat.format(todaysDate), dateFormat.format(afterVisitSummaries.get(0).getCreatedDate()));
        assertEquals("Actual PDf is not equal to Expected PDF", true, Arrays.equals(expectedPdfData, afterVisitSummaries.get(0).getBase64EncodedPDF()));
    }

    @Test
    public void testMarshallingJaxbIntroductionMappingsToXMLForAfterVisitSummarySearchRequest() throws Exception {
        String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><AfterVisitSummarySearchRequest><afterVisitSummarySearchCriteria><clientId>12345</clientId><veteranId>54321</veteranId><startDate>2008-09-28</startDate><endDate>2014-09-18</endDate></afterVisitSummarySearchCriteria></AfterVisitSummarySearchRequest>";
        StringWriter sw = new StringWriter();

        JaxbIntros config = IntroductionsConfigParser.parseConfig(new ClassPathResource("spring/jaxb-intros-marshaller-mapping.xml").getInputStream());
        IntroductionsAnnotationReader reader = new IntroductionsAnnotationReader(config);
        Map<String, Object> jaxbConfig = new HashMap<String, Object>();

        //jaxbConfig.put(JAXBRIContext.DEFAULT_NAMESPACE_REMAP, "http://localhost:8080/va-avs-ws/schemas");
        jaxbConfig.put(JAXBRIContext.ANNOTATION_READER, reader);
        JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {AfterVisitSummarySearchRequest.class}, jaxbConfig);
        jaxb2Marshaller.marshal(getAfterVisitSummarySearchRequest(), new StreamResult(sw));

        assertEquals(expectedXML, sw.toString());
    }

    @Test
    public void testUnmarshallingXMLToJaxbIntroductionMappingsForAfterVisitSummarySearchRequest() throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String actualXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><AfterVisitSummarySearchRequest><afterVisitSummarySearchCriteria><clientId>12345</clientId><veteranId>54321</veteranId><startDate>2008-09-28</startDate><endDate>2014-09-18</endDate></afterVisitSummarySearchCriteria></AfterVisitSummarySearchRequest>";
        StringReader reader = new StringReader(actualXML);
        AfterVisitSummarySearchRequest avsSearchRequest = (AfterVisitSummarySearchRequest) jaxb2Marshaller.unmarshal(new StreamSource(reader));

        assertNotNull("null Search Request", avsSearchRequest);
        assertNotNull("null Search Criteria", avsSearchRequest.getAfterVisitSummarySearchCriteria());
        assertEquals("12345", "12345", avsSearchRequest.getAfterVisitSummarySearchCriteria().getClientId());
        assertEquals("54321", "54321", avsSearchRequest.getAfterVisitSummarySearchCriteria().getVeteranId());
        assertEquals("2008-09-28", "2008-09-28", dateFormat.format(avsSearchRequest.getAfterVisitSummarySearchCriteria().getStartDate()));
        assertEquals("2014-09-18", "2014-09-18", dateFormat.format(avsSearchRequest.getAfterVisitSummarySearchCriteria().getEndDate()));
    }

    private AfterVisitSummarySearchRequest getAfterVisitSummarySearchRequest() throws Exception{
        DateAdapter dta = new DateAdapter();
        Date date20080928 = dta.unmarshal("2008-09-28");
        Date date20140918 = dta.unmarshal("2014-09-18");
        AfterVisitSummarySearchCriteria searchCriteria = new AfterVisitSummarySearchCriteria("12345", "54321", date20080928, date20140918);
        AfterVisitSummarySearchRequest request = new AfterVisitSummarySearchRequest(searchCriteria);
        return request;
    }

    private WebServiceResponse getWebServiceResponse(Date avsDate) {
        List<WebServiceResponseMessage> messages = new ArrayList<WebServiceResponseMessage>();
        messages.add(new WebServiceResponseMessage("AVS Found."));
        WebServiceResponseStatus status = new WebServiceResponseStatus(WebServiceResponseStatus.Request.Successful, messages);
        AfterVisitSummary avs = new AfterVisitSummary("1", "54321", "Pouncil AVS", "AVS for Mr. Pouncil.", avsDate, getPdf("avs-test.pdf"));

        List<AfterVisitSummary> afterVisitSummaries = new ArrayList<AfterVisitSummary>();
        afterVisitSummaries.add(avs);

        WebServicePayload payload = new WebServicePayload(afterVisitSummaries);
        WebServiceResponse response = new WebServiceResponse(status, payload);
        return response;
    }

    private Byte[] convertToByteArray(byte[] bytes) {
        Byte[] byteObjects = new Byte[bytes.length];

        int i=0;
        // Associating Byte array values with bytes. (byte[] to Byte[])
        for(byte b: bytes) {
            byteObjects[i++] = b;  // Autoboxing.
        }

        return byteObjects;
    }

    private byte[] getPdf(String pdfPath) {
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