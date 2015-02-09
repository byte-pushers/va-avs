package gov.va.med.avs.model;

/**
 * Created by Tonté Pouncil on 2/8/15.
 */
public class WebServiceResponseMessage {
    private String value;

    public WebServiceResponseMessage(){

    }

    public WebServiceResponseMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
