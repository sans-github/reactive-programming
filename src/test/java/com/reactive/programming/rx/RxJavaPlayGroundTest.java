package com.reactive.programming.rx;

import com.reactive.programming.web.util.DelayService;
import com.reactive.programming.web.util.DelayServiceImpl;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxJavaPlayGroundTest {

    private static final Logger logger = LoggerFactory.getLogger(RxJavaPlayGroundTest.class);

    private DelayService delayService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        delayService = new DelayServiceImpl();
    }

    @Test
    public void testSubscription() {

        final Observable<String> observable = getSingleStringObservable();
        final Observer<String> observer = getStringObserver();

        observable.subscribe(observer);
    }

    @Test
    public void testSubscriptionWithError() {

        expectedException.expect(RuntimeException.class);

        final Observable<String> observable = getSingleStringObservableWithError();
        final Observer<String> observer = getStringObserver();

        observable.subscribe(observer);
    }

    @Test
    public void testSyncEmissionAndSyncProcessing() {
        //notice emission and subscription-processing happens on the main thread
        final Observable<String> observable = getSingleStringObservable();
        final Observer<String> observer = getStringObserver();

        observable
                .map(s -> {
                    logger.info("To upper case");
                    return s.toUpperCase();
                })
                .map(s -> {
                    logger.info("To lower case");
                    return s.toLowerCase();
                })
                .subscribe(observer);
    }

    @Test
    public void testSyncEmissionAndASyncProcessing() {
        //notice emission happens on main thread, and subscription-processing happens on computation thread
        final Observable<String> observable = getSingleStringObservable();
        final Observer<String> observer = getStringObserver();

        observable
                .map(s -> {
                    logger.info("To upper case");
                    return s.toUpperCase();
                })
                .map(s -> {
                    logger.info("To lower case");
                    return s.toLowerCase();
                })
                .observeOn(Schedulers.computation())
                .subscribe(observer);

        delayService.delay(200); //hold program termination to let all log statements complete
    }

    @Test
    public void testASyncEmissionAndSyncSubscription() {

        //notice emission and subscription-processing happens on the non-main thread
        final Observable<String> observable = getSingleStringObservable();
        final Observer<String> observer = getStringObserver();

        observable
                .subscribeOn(Schedulers.io())
                .map(s -> {
                    logger.info("To upper case");
                    return s.toUpperCase();
                })
                .map(s -> {
                    logger.info("To lower case");
                    return s.toLowerCase();
                })
                .subscribe(observer);

        delayService.delay(200); //hold program termination to let all log statements complete
    }

    @Test
    public void testASyncEmissionAndASyncSubscription() {

        //notice emission and processing happens on the non-main thread
        final Observable<String> observable = getSingleStringObservable();
        final Observer<String> observer = getStringObserver();

        observable
                .subscribeOn(Schedulers.io())
                .map(s -> {
                    logger.info("To upper case");
                    return s.toUpperCase();
                })
                .map(s -> {
                    logger.info("To lower case");
                    return s.toLowerCase();
                }).observeOn(Schedulers.computation())
                .subscribe(observer);

        delayService.delay(200); //hold program termination to let all log statements complete
    }

    //Observable Contract:  onNext() must be called sequentially and never concurrently by more than one thread at a time
    @Test
    public void testObservableWithSerialEmission() {
        Observable.range(1, 10).subscribeOn(Schedulers.io()).map(integer -> {
            logger.info("Shifting {} up ", integer);
            return ++integer;
        }).map(integer -> {
            logger.info("Shifting {} down ", integer);
            return --integer;
        }).subscribe(integer -> logger.info("Received {}", integer));
        delayService.delay(200);
    }

    @Test
    public void testObservableWithParallelEmission() {
        Observable.range(1, 10)
                .flatMap(value -> Observable.just(value)
                        .subscribeOn(Schedulers.io())
                        .map(integer -> {
                            logger.info("Shifting {} up ", integer);
                            return ++integer;
                        }))
                .map(integer -> {
                    logger.info("Shifting {} down ", integer);
                    return --integer;
                })
                .subscribe(integer -> logger.info("Received {}", integer));

        delayService.delay(200);
    }

    private Observable<String> getSingleStringObservable() {

        return Observable.create(observableOnSubscribe -> {
            observableOnSubscribe.onNext(StringGenerationService.generate());
            observableOnSubscribe.onComplete();
        });
    }

    private Observable<String> getSingleStringObservableWithError() {
        return Observable.create(observableOnSubscribe -> {
            logger.info("Creating Observable");

            observableOnSubscribe.onNext(StringGenerationService.errWhenGenerating());
            observableOnSubscribe.onComplete();
        });
    }

    private Observer<String> getStringObserver() {

        return new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull final Disposable d) {
                logger.info("Observer.onSubscribe");
            }

            @Override
            public void onNext(@NonNull final String s) {
                //conveys an item that is emitted by the Observable to the observer
                logger.info("Observer processing the string [{}] with no-op", s);
            }

            @Override
            public void onError(@NonNull final Throwable e) {
                //indicates that the Observable has terminated with a specified error condition and that it will be emitting no further items
                logger.info("Observer.onError ", e);
                throw new RuntimeException(e);
            }

            @Override
            public void onComplete() {
                //indicates that the Observable has completed successfully and that it will be emitting no further items
                logger.info("Observer.onCompleted");
            }
        };
    }

    private static class StringGenerationService {
        private static String generate() {
            return RandomStringUtils.randomAlphabetic(10);
        }

        private static String errWhenGenerating() {
            throw new RuntimeException("Exception generating string");
        }
    }

}
