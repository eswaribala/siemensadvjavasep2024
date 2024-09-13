package com.siemens;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

import java.util.concurrent.TimeUnit;

public class BackpressureDropExample {
    public static void main(String[] args) throws InterruptedException {
        // Create a Flowable that emits items at fixed intervals
        Flowable<Long> source = Flowable.interval(20, TimeUnit.MILLISECONDS)
                .onBackpressureDrop(item -> System.out.println("Dropped: " + item));

        // Subscribe with a slow consumer
        source
                .observeOn(Schedulers.computation())
                .subscribe(new DisposableSubscriber<Long>() {
                    @Override
                    public void onNext(Long item) {
                        System.out.println("Received: " + item);
                        try {
                            // Simulating slow processing
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.err.println("Error: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });

        // Keep the application alive for a while to observe behavior
        Thread.sleep(5000);
    }
}