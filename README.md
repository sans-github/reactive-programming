# reactive-programming
Experiment with reactive programming

## Endpoints for ReactiveDemoController
### /sync
    curl -sk  http://localhost:58880/rxjava/sync -H 'Content-Type: application/json'  | python -m json.tool
    curl -sk  http://localhost:58880/java9/sync -H 'Content-Type: application/json'  | python -m json.tool

### /reactive-sync
    curl -sk  http://localhost:58880/rxjava/reactive-sync -H 'Content-Type: application/json'  | python -m json.tool
    curl -sk  http://localhost:58880/java9/reactive-sync -H 'Content-Type: application/json'  | python -m json.tool

### /reactive-async-with-subscribeOn-rxIoThreadPool
    curl -sk  http://localhost:58880/rxjava/reactive-async-with-subscribeOn-rxIoThreadPool -H 'Content-Type: application/json'  | python -m json.tool
    curl -sk  http://localhost:58880/java9/reactive-async-with-subscribeOn-rxIoThreadPool -H 'Content-Type: application/json'  | python -m json.tool

### /reactive-async-with-subscribeOn-customThreadPool
    curl -sk  http://localhost:58880/rxjava/reactive-async-with-subscribeOn-customThreadPool -H 'Content-Type: application/json'  | python -m json.tool
    curl -sk  http://localhost:58880/java9/reactive-async-with-subscribeOn-customThreadPool -H 'Content-Type: application/json'  | python -m json.tool


## Endpoints for MultiThreadController
    This is *not* related to rxjava.
    This is only to experiment various java based multi-threading approach.

### /request
    curl -sk http://localhost:58880/rxjava/request -H "Content-Type: application/json"
    curl -sk http://localhost:58880/java9/request -H "Content-Type: application/json"

### /request-callable
    curl -sk http://localhost:58880/rxjava/request-callable -H "Content-Type: application/json"
    curl -sk http://localhost:58880/java9/request-callable -H "Content-Type: application/json"

### /request-deferred-result
    curl -sk http://localhost:58880/rxjava/request-deferred-result -H "Content-Type: application/json"
    curl -sk http://localhost:58880/java9/request-deferred-result -H "Content-Type: application/json"

### /request-deferred-result-cached-thread-pool
    curl -sk http://localhost:58880/rxjava/request-deferred-result-cached-thread-pool -H "Content-Type: application/json"
    curl -sk http://localhost:58880/java9/request-deferred-result-cached-thread-pool -H "Content-Type: application/json"

### /request-deferred-result-fixed-sized-thread-pool-20
    curl -sk http://localhost:58880/rxjava/request-deferred-result-fixed-sized-thread-pool-20 -H "Content-Type: application/json"
    curl -sk http://localhost:58880/java9/request-deferred-result-fixed-sized-thread-pool-20 -H "Content-Type: application/json"

### /completable-future
    curl -sk http://localhost:58880/rxjava/completable-future -H "Content-Type: application/json"
    curl -sk http://localhost:58880/java9/completable-future -H "Content-Type: application/json"
