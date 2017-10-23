package com.reactive.programming.web.domain;

public class ExternalRequest {
    private String externalRequestHeader;
    private String externalRequestBody;
    private String externalRequestCookie;

    public String getExternalRequestHeader() {
        return externalRequestHeader;
    }

    public void setExternalRequestHeader(final String externalRequestHeader) {
        this.externalRequestHeader = externalRequestHeader;
    }

    public String getExternalRequestBody() {
        return externalRequestBody;
    }

    public void setExternalRequestBody(final String externalRequestBody) {
        this.externalRequestBody = externalRequestBody;
    }

    public String getExternalRequestCookie() {
        return externalRequestCookie;
    }

    public void setExternalRequestCookie(final String externalRequestCookie) {
        this.externalRequestCookie = externalRequestCookie;
    }
}
