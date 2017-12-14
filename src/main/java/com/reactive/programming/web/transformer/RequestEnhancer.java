package com.reactive.programming.web.transformer;

import com.reactive.programming.web.domain.ExternalRequest;
import com.reactive.programming.web.util.DelayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestEnhancer {

    private static final Logger logger = LoggerFactory.getLogger(RequestEnhancer.class);
    private static final String REQUEST_ENHANCED = " :: Request_enhanced";

    @Autowired
    private final DelayService delayService;

    public RequestEnhancer(final DelayService delayService) {
        this.delayService = delayService;
    }

    public ExternalRequest enhance(final ExternalRequest externalRequest) {
        delayService.delay(100);
        externalRequest.setExternalRequestCookie(externalRequest.getExternalRequestCookie() + REQUEST_ENHANCED);
        externalRequest.setExternalRequestHeader(externalRequest.getExternalRequestHeader() + REQUEST_ENHANCED);
        externalRequest.setExternalRequestBody(externalRequest.getExternalRequestBody() + REQUEST_ENHANCED);
        logger.info("Sending enhanced request");

        return externalRequest;
    }
}
