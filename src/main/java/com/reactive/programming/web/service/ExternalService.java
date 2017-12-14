package com.reactive.programming.web.service;

import com.reactive.programming.web.domain.ExternalRequest;
import com.reactive.programming.web.domain.ExternalResponse;
import com.reactive.programming.web.util.DelayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String SERVICE_CALL = " :: Service_call";

    @Autowired
    private final DelayService delayService;

    public ExternalService(final DelayService delayService) {
        this.delayService = delayService;
    }

    public ExternalResponse execute(final ExternalRequest externalRequest) {
        delayService.delay(100);
        final ExternalResponse externalResponse = new ExternalResponse();
        externalResponse.setExternalResponseBody(externalRequest.getExternalRequestBody() + SERVICE_CALL);
        externalResponse.setExternalResponseCookie(externalRequest.getExternalRequestCookie() + SERVICE_CALL);
        externalResponse.setExternalResponseHeader(externalRequest.getExternalRequestHeader() + SERVICE_CALL);
        logger.info("Sending response from externalService: ");
        return externalResponse;
    }
}
