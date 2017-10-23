package com.reactive.programming.web.transformer;

import com.reactive.programming.web.domain.ExternalRequest;
import com.reactive.programming.web.domain.InternalRequest;
import com.reactive.programming.web.util.DelayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class RequestTransformer {
    private static final Logger logger = LoggerFactory.getLogger(RequestTransformer.class);
    @Autowired
    private final DelayService delayService;

    private static final String REQUEST_TRANSFORMED = " :: Request_transformed";

    public RequestTransformer(final DelayService delayService) {
        this.delayService = delayService;
    }

    public ExternalRequest transform(final InternalRequest internalRequest) {
        delayService.delay(100);
        final ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setExternalRequestBody(internalRequest.getInternalRequestBody() + REQUEST_TRANSFORMED);
        externalRequest.setExternalRequestHeader(internalRequest.getInternalRequestHeader() + REQUEST_TRANSFORMED);
        externalRequest.setExternalRequestCookie(internalRequest.getInternalRequestCookie() + REQUEST_TRANSFORMED);

        logger.info("Sending transformed request");

        return externalRequest;
    }
}
