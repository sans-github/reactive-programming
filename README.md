# reactive-programming
Experiment with reactive programming

## Endpoints for ReactiveDemoController
### /sync
    curl -sk  http://localhost:58880/sync -H 'Content-Type: application/json'  | python -m json.tool
    
### /reactive-sync
    curl -sk  http://localhost:58880/reactive-sync -H 'Content-Type: application/json'  | python -m json.tool
    
### /reactive-async-with-subscribeOn-rxIoThreadPool
    curl -sk  http://localhost:58880/reactive-async-with-subscribeOn-rxIoThreadPool -H 'Content-Type: application/json'  | python -m json.tool

### /reactive-async-with-subscribeOn-customThreadPool
    curl -sk  http://localhost:58880/reactive-async-with-subscribeOn-customThreadPool -H 'Content-Type: application/json'  | python -m json.tool

    
## Endpoints for MultiThreadController 
    This is *not* related to rxjava.
    I utilize it to experiment this for various hava based multi-threading approach.     

### /request
    curl -sk http://localhost:58880/request -H "Content-Type: application/json"

### /request-callable
    curl -sk http://localhost:58880/request-callable -H "Content-Type: application/json"

### /request-deferred-result     
    curl -sk http://localhost:58880/request-deferred-result -H "Content-Type: application/json"
    
### /request-deferred-result-cached-thread-pool     
    curl -sk http://localhost:58880/request-deferred-result-cached-thread-pool -H "Content-Type: application/json"

### /request-deferred-result-fixed-sized-thread-pool-20
    curl -sk http://localhost:58880/request-deferred-result-fixed-sized-thread-pool-20 -H "Content-Type: application/json"

### /completable-future
    curl -sk http://localhost:58880/completable-future -H "Content-Type: application/json"
