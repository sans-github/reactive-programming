package com.reactive.programming.web.controller;

import com.reactive.programming.web.util.DelayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * References
 * https://spring.io/blog/2012/05/10/spring-mvc-3-2-preview-making-a-controller-method-asynchronous/
 * http://callistaenterprise.se/blogg/teknik/2014/04/22/c10k-developing-non-blocking-rest-services-with-spring-mvc/
 * http://xpadro.blogspot.com/2015/07/understanding-callable-and-spring.html
 */
@RestController
@RequestMapping(value = "/", produces = "application/json")
public class MultiThreadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DelayService delayService;

    @RequestMapping(value = "/request", method = GET)
    @ResponseBody
    public String request() {
        logger.info("Request received");
        return delayService.delay();
    }

    /**
     * By default Spring MVC uses a SimpleAsyncTaskExecutor to execute Callable instances returned by controller methods.
     */
    @RequestMapping(value = "/request-callable", method = GET)
    @ResponseBody
    public Callable<String> requestWithCallable() {
        logger.info("Request received");
        return delayService::delay;
    }

    @RequestMapping(value = "/request-deferred-result", method = GET)
    @ResponseBody
    public DeferredResult<String> requestWithDeferredResult() {
        logger.info("Request received");
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(delayService::delay).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
        return deferredResult;
    }

    @RequestMapping(value = "/request-deferred-result-cached-thread-pool", method = GET)
    @ResponseBody
    public DeferredResult<String> requestWithDeferredResultAndCachedThreadPool() {
        logger.info("Request received");
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        final ExecutorService executorService = newCachedThreadPool();
        CompletableFuture.supplyAsync(delayService::delay, executorService).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
        return deferredResult;
    }

    @RequestMapping(value = "/request-deferred-result-fixed-sized-thread-pool-20", method = GET)
    @ResponseBody
    public DeferredResult<String> requestWithDeferredResultAndFixedSizedThreadPool20() {
        logger.info("Request received");
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(delayService::delay, newFixedThreadPool(20)).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
        return deferredResult;
    }

    @RequestMapping(value = "/completable-future", method = GET)
    @ResponseBody
    public CompletableFuture<String> requestWithCompletableFuture() {
        logger.info("Request received");
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        return CompletableFuture.supplyAsync(delayService::delay, newCachedThreadPool()).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
    }
}
