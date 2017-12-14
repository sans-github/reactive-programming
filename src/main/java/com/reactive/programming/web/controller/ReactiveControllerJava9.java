package com.reactive.programming.web.controller;

import com.reactive.programming.web.domain.InternalResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping(value = "/java9", produces = "application/json")
public class ReactiveControllerJava9 extends ReactiveControllerBase {

    private static final Logger logger = LoggerFactory.getLogger(ReactiveControllerJava9.class);

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InternalResponse> sync() {
        logger.info("Not implemented");
        return null;
    }

    @RequestMapping(value = "/reactive-sync", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseEntity<InternalResponse>> reactiveSync() {
        logger.info("Not implemented");
        return null;
    }

    @RequestMapping(value = "/reactive-async-with-subscribeOn-rxIoThreadPool", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseEntity<InternalResponse>> reactiveASyncWithSubscribeOnRxIoThreadPool() {
        logger.info("Not implemented");
        return null;
    }

    @RequestMapping(value = "/reactive-async-with-subscribeOn-customThreadPool", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseEntity<InternalResponse>> reactiveASyncWithSubscribeOnCustomThreadPool() {
        logger.info("Not implemented");
        return null;
    }
}
