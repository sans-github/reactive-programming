package com.reactive.programming.web.controller;

import com.reactive.programming.web.domain.InternalRequest;
import com.reactive.programming.web.domain.InternalResponse;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/rxjava", produces = "application/json")
public class ReactiveControllerRxJava extends ReactiveControllerBase {

    private static final Logger logger = LoggerFactory.getLogger(ReactiveControllerRxJava.class);

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InternalResponse> sync() {

        logger.info("Request received on /sync");

        return Optional.of(createRequest())
                .map(internalRequest -> requestTransformer.transform(internalRequest))  //this is java.util.Optional.map
                .map(internalRequestEnhanced -> requestEnhancer.enhance(internalRequestEnhanced))
                .map(externalRequest -> externalService.execute(externalRequest))
                .map(externalResponse -> responseTransformer.transform(externalResponse))
                .map(internalResponse -> responseEnhancer.enhance(internalResponse))
                .map(internalResponseEnhanced -> new ResponseEntity<>(internalResponseEnhanced, OK))
                .orElseGet(() -> new ResponseEntity<>(INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/reactive-sync", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseEntity<InternalResponse>> reactiveSync() {

        logger.info("Request received on /reactive-sync. This is not async operation. By default, emissions happen on the same main thread");

        final DeferredResult<ResponseEntity<InternalResponse>> deferredResult = new DeferredResult<>();

        createRequestObservable()
                .map(requestTransformer::transform) //this is io.reactivex.Observable.map
                .map(requestEnhancer::enhance)
                .map(externalService::execute)
                .map(responseTransformer::transform)
                .map(responseEnhancer::enhance)
                .subscribe(internalResponse -> deferredResult.setResult(new ResponseEntity<>(internalResponse, OK)));
        return deferredResult;
    }

    @RequestMapping(value = "/reactive-async-with-subscribeOn-rxIoThreadPool", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseEntity<InternalResponse>> reactiveASyncWithSubscribeOnRxIoThreadPool() {

        logger.info("Request received on /reactive-async-with-subscribeOn-rxIoThreadPool");
        final DeferredResult<ResponseEntity<InternalResponse>> deferredResult = new DeferredResult<>();

        logger.info("Built-in Schedulers.io to subscribe observables on");
        final Scheduler scheduler = Schedulers.io();

        createRequestObservable()
                .subscribeOn(scheduler)
                .map(requestTransformer::transform)
                .map(requestEnhancer::enhance)
                .map(externalService::execute)
                .map(responseTransformer::transform)
                .map(responseEnhancer::enhance)
                .subscribe(internalResponse -> {
                    deferredResult.setResult(new ResponseEntity<>(internalResponse, OK));
                });
        return deferredResult;
    }

    @RequestMapping(value = "/reactive-async-with-subscribeOn-customThreadPool", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseEntity<InternalResponse>> reactiveASyncWithSubscribeOnCustomThreadPool() {

        logger.info("Request received on /reactive-async-with-subscribeOn-customThreadPool");
        final DeferredResult<ResponseEntity<InternalResponse>> deferredResult = new DeferredResult<>();

        logger.info("Customer scheduler to subscribe observables on");
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Scheduler scheduler = Schedulers.from(executor);

        createRequestObservable()
                .subscribeOn(scheduler)
                .map(requestTransformer::transform)
                .map(requestEnhancer::enhance)
                .map(externalService::execute)
                .map(responseTransformer::transform)
                .map(responseEnhancer::enhance).subscribe(
                internalResponse -> {
                    deferredResult.setResult(new ResponseEntity<>(internalResponse, OK));
                },
                throwable -> {
                },
                executor::shutdownNow);
        return deferredResult;
    }

    private InternalRequest createRequest() {
        return new InternalRequest() {{
            setInternalRequestBody("body-" + randomAlphabetic(10));
            setInternalRequestCookie("cookie-" + randomAlphabetic(10));
            setInternalRequestHeader("header-" + randomAlphabetic(10));
        }};
    }

    private Observable<InternalRequest> createRequestObservable() {
        return Observable.create(e -> e.onNext(new InternalRequest() {{
            setInternalRequestBody("body-" + randomAlphabetic(10));
            setInternalRequestCookie("cookie-" + randomAlphabetic(10));
            setInternalRequestHeader("header-" + randomAlphabetic(10));
        }}));
    }
}
