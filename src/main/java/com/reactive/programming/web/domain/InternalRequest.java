package com.reactive.programming.web.domain;

public class InternalRequest {
    private String internalRequestHeader;
    private String internalRequestBody;
    private String internalRequestCookie;

    public String getInternalRequestHeader() {
        return internalRequestHeader;
    }

    public void setInternalRequestHeader(final String internalRequestHeader) {
        this.internalRequestHeader = internalRequestHeader;
    }

    public String getInternalRequestBody() {
        return internalRequestBody;
    }

    public void setInternalRequestBody(final String internalRequestBody) {
        this.internalRequestBody = internalRequestBody;
    }

    public String getInternalRequestCookie() {
        return internalRequestCookie;
    }

    public void setInternalRequestCookie(final String internalRequestCookie) {
        this.internalRequestCookie = internalRequestCookie;
    }
}
