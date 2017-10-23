# How is reactive programming different than imperative    
## Scenario: Imperative steps
    Say you are writing an app that calls the weather service hosted by weather.com
    
        Incoming Request 
             Transform Request      : Transform request to the format needed for weather.com API
             Enhance Request        : weather.com mandates you to include your app name. Enhance the request by adding app name to the request.
             Call Service           : Call the API at weahter.com
             Transform Response     : Translate response to the format that your app client understands
            Enrich Response         : Add advertisement to response
        Send response
        
        See /sync in ReactiveDemoController
        
## Reactive steps
       Get the request
            Express interest (by subscribing) 
            Ask to be notified when request transformation is completed.
            Ask to be notified when request enhancement is completed. 
            Ask to be notified when service finishes.
            Ask to be notified when response transformation is completed.
            Ask to be notified when response enhancement is completed.
       Send Response
      
# RxJavaPlayGroundTest - 
    Inspect which tread calls the log statement. 
    Even if we use observable, it is sync operation by default. 
    "By default, the thread that declares the subscribe() is the one that pushes items from the source all the way up the chain to the Subscriber"

    If we wanted to subscribe to this Observable but we do not want to do it on the current main thread, we can do that with the subscribeOn() and specify a Scheduler. This will emit the items from the source on a different thread.
    Q - why is there sleep method in all the async methods? 

# callReactiveEndpoints
    Why did the timing not improve?

# Explain workflow of ReactiveDemoController
    Q - Why do we need executor::shutdownNow on reactive-async-with-subscribeOn-customThreadPool?

# Stress test
    jmeter -t Reactive\ Demo.jmx &
    start visualVm
    Where did the threading happen? - because the subscription now occurs on a different thread, the subscribe() call will return control to the thread that called it almost immediately

# References 
    * http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/
    * https://github.com/ReactiveX/RxJava/wiki/Additional-Reading
    * https://github.com/jhusain/learnrxjava/blob/master/README.md
    * https://spring.io/blog/2012/05/10/spring-mvc-3-2-preview-making-a-controller-method-asynchronous/
    * http://callistaenterprise.se/blogg/teknik/2014/04/22/c10k-developing-non-blocking-rest-services-with-spring-mvc/
    * http://akarnokd.blogspot.hu/
    * http://www.java-allandsundry.com/2015/06/rx-java-subscribeon-and-observeon.html
    * http://octodecillion.com/blog/java-hello-world-using-rxjava-library/
    * http://reactivex.io/intro.html
    * http://akarnokd.blogspot.hu/
    * http://xpadro.blogspot.com/2015/07/understanding-callable-and-spring.html
    * http://www.grahamlea.com/2014/07/rxjava-threading-examples/
    * http://callistaenterprise.se/blogg/teknik/2015/05/20/blog-series-building-microservices/
