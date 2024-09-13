package com.rps.banking.utility;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaAsyncWithBackpressureDropExample {

    public static void main(String[] args) throws InterruptedException {

        Flowable<Object> flowableAsyncBackp = Flowable.create(emitter -> {

                    // Publish 1000 numbers
                    for (int i = 0; i < 1000; i++) {
                        System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
                        emitter.onNext(i);
                    }
                    // When all values or emitted, call complete.
                    emitter.onComplete();
                }, BackpressureStrategy.DROP)
                .onBackpressureDrop(i -> System.out.println(Thread.currentThread().getName() + " | DROPPED = " + i));

        flowableAsyncBackp.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.single()).subscribe(i -> {
            // Process received value.
            System.out.println(Thread.currentThread().getName() + " | Received = " + i);
            // 500 mills delay to simulate slow subscriber
            Thread.sleep(500);
        });
        /*
         * Notice above -
         *
         * BackpressureStrategy.DROP - If subscriber can't keep up with values, then
         * drop the values.
         *
         * subscribeOn & observeOn - Put subscriber & publishers on different threads.
         */

        // Since publisher & subscriber run on different thread than main thread, keep
        // main thread active for 100 seconds.
        Thread.sleep(100000);
    }
}
