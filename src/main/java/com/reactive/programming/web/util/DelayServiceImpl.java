package com.reactive.programming.web.util;

import org.springframework.stereotype.Service;

@Service
public class DelayServiceImpl implements DelayService {
    private static final Integer DEFAULT_SLEEP_INTERVAL_IN_MILLIS = 500;

    @Override
    public String delay() {
        return delay(DEFAULT_SLEEP_INTERVAL_IN_MILLIS);
    }

    @Override
    public String delay(final long delay) {
        try {
            Thread.sleep(delay);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return "Task finished\n";
    }
}
