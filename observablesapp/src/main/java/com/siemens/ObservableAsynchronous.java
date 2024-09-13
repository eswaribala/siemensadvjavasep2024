package com.siemens;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
//publisher not waiting for subscriber
public class ObservableAsynchronous {

	public static void main(String[] args) throws InterruptedException {

		Observable<Object> observableSync = Observable.create(emitter -> {

			// Publish 100 numbers
			for (int i = 0; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
				// Publish or emit a value with 10 ms delay
				Thread.sleep(10);
				emitter.onNext(i);
			}
			// When all values or emitted, call complete.
			emitter.onComplete();
		}).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());
		/*
		 * Notice above - subscribeOn & observeOn puts subscriber & publisher/observable
		 * on different threads.
		 */

		observableSync.subscribe(i -> {
			// Process received value.
			System.out.println(Thread.currentThread().getName() + " | Received = " + i);
			// 100 mills delay to simulate slow subscriber
			Thread.sleep(100);
		});

		// Since publisher & subscriber run on different thread than main thread, keep
		// main thread active for 5 seconds.
		Thread.sleep(5000);
	}
}
