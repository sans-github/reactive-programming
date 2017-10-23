package com.reactive.programming.web.domain;

public class ExternalResponse {
    private String externalResponseHeader;
    private String externalResponseBody;
    private String externalResponseCookie;

    public String getExternalResponseHeader() {
        return externalResponseHeader;
    }

    public void setExternalResponseHeader(final String externalResponseHeader) {
        this.externalResponseHeader = externalResponseHeader;
    }

    public String getExternalResponseBody() {
        return externalResponseBody;
    }

    public void setExternalResponseBody(final String externalResponseBody) {
        this.externalResponseBody = externalResponseBody;
    }

    public String getExternalResponseCookie() {
        return externalResponseCookie;
    }

    public void setExternalResponseCookie(final String externalResponseCookie) {
        this.externalResponseCookie = externalResponseCookie;
    }
}
