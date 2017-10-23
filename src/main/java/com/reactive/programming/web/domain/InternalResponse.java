package com.reactive.programming.web.domain;

public class InternalResponse {
    private String internalResponseHeader;
    private String internalResponseBody;
    private String internalResponseCookie;

    public String getInternalResponseHeader() {
        return internalResponseHeader;
    }

    public void setInternalResponseHeader(final String internalResponseHeader) {
        this.internalResponseHeader = internalResponseHeader;
    }

    public String getInternalResponseBody() {
        return internalResponseBody;
    }

    public void setInternalResponseBody(final String internalResponseBody) {
        this.internalResponseBody = internalResponseBody;
    }

    public String getInternalResponseCookie() {
        return internalResponseCookie;
    }

    public void setInternalResponseCookie(final String internalResponseCookie) {
        this.internalResponseCookie = internalResponseCookie;
    }
}
