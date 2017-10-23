package com.reactive.programming.web.transformer;

import com.reactive.programming.web.domain.ExternalResponse;
import com.reactive.programming.web.domain.InternalResponse;
import com.reactive.programming.web.util.DelayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseTransformer {

    private static final Logger logger = LoggerFactory.getLogger(ResponseTransformer.class);
    private static final String RESPONSE_TRANSFORMED = " :: Response_transformed";

    @Autowired
    private final DelayService delayService;

    public ResponseTransformer(final DelayService delayService) {
        this.delayService = delayService;
    }

    public InternalResponse transform(final ExternalResponse externalResponse) {
        delayService.delay(100);
        final InternalResponse internalResponse = new InternalResponse();
        internalResponse.setInternalResponseHeader(externalResponse.getExternalResponseHeader() + RESPONSE_TRANSFORMED);
        internalResponse.setInternalResponseCookie(externalResponse.getExternalResponseCookie() + RESPONSE_TRANSFORMED);
        internalResponse.setInternalResponseBody(externalResponse.getExternalResponseBody() + RESPONSE_TRANSFORMED);
        logger.info("Sending transformed response");

        return internalResponse;
    }
}
