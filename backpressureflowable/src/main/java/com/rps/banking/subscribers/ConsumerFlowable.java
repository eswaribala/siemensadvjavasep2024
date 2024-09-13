package com.rps.banking.subscribers;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import io.reactivex.subscribers.DefaultSubscriber;


public class ConsumerFlowable extends DefaultSubscriber<Integer> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ConsumerFlowable.class);

    @Override
    protected void onStart() {
        request(1);
    }

    @Override
    public void onNext(Integer i) {
        logger.info("consuming {}", i);
        if (0 == (i % 5)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
                // can be ignored, just used for pausing
            }
        }
        request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("error received", throwable);
    }

    @Override
    public void onComplete() {
        logger.info("consumer finished");
    }
}