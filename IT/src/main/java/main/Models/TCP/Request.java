package main.Models.TCP;

import com.google.gson.annotations.Expose;

import main.Enums.RequestType;

public class Request {
	@Expose
    private RequestType requestType;
	@Expose
    private String requestMessage;

    public Request(RequestType requestType, String requestMessage) {
        this.requestType = requestType;
        this.requestMessage = requestMessage;
    }
    public Request(){

    }
    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}
