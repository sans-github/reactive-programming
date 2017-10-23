package com.reactive.programming.web.transformer;

import com.reactive.programming.web.domain.InternalResponse;
import com.reactive.programming.web.util.DelayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseEnhancer {

    private static final Logger logger = LoggerFactory.getLogger(ResponseEnhancer.class);
    private static final String RESPONSE_ENHANCED = " :: Response_enhanced";

    @Autowired
    private final DelayService delayService;

    public ResponseEnhancer(final DelayService delayService) {
        this.delayService = delayService;
    }

    public InternalResponse enhance(final InternalResponse internalResponse) {
        delayService.delay(100);
        internalResponse.setInternalResponseBody(internalResponse.getInternalResponseBody() + RESPONSE_ENHANCED);
        internalResponse.setInternalResponseCookie(internalResponse.getInternalResponseCookie() + RESPONSE_ENHANCED);
        internalResponse.setInternalResponseHeader(internalResponse.getInternalResponseHeader() + RESPONSE_ENHANCED);
        logger.info("Sending enhanced response");

        return internalResponse;
    }
}
