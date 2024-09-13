package com.siemens;

import io.reactivex.Observable;

public class ObservableSynchronous {
    public static void main(String[] args){
        Observable<Object> observableSync = Observable.create(emitter -> {

            // Publish 100 numbers
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
                // Publish or emit a value.
                emitter.onNext(i);
            }
            // When all values or emitted, call complete.
            emitter.onComplete();
        });

        observableSync.subscribe(i -> {
            // Process received value.
            System.out.println(Thread.currentThread().getName() + " | Received = " + i);
            // 100 mills delay to simulate slow subscriber
            Thread.sleep(100);
        });
    }
}
