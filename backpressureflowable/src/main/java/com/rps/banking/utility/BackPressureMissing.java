package com.rps.banking.utility;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.schedulers.Schedulers;
/*
 * With MISSING strategy, as name suggests there is no buffering or dropping. 
 * Subscriber must handle overflow else they will receive error.


 */
public class BackPressureMissing {
	public static void main(String[] args) throws InterruptedException {
		 
		Flowable<Object> flowableAsyncBackp = Flowable.create(emitter -> {
 
			// Publish 1000 numbers
			for (int i = 0; i < 1000; i++) {
				System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
				// BackpressureStrategy.MISSING will cause MissingBackpressureException
				// eventually
				try {
					emitter.onNext(i);
				} catch (MissingBackpressureException exception) {
					emitter.onError(exception);
				}
			}
			// When all values or emitted, call complete.
			emitter.onComplete();
 
		}, BackpressureStrategy.MISSING);
 
		flowableAsyncBackp.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.single()).subscribe(i -> {
			// Process received value.
			System.out.println(Thread.currentThread().getName() + " | Received = " + i);
		}, e -> {
			// Process error
			System.err.println(Thread.currentThread().getName() + " | Error = " + e.getClass().getSimpleName() + " "
					+ e.getMessage());
		});
		/*
		 * Notice above -
		 * 
		 * subscribeOn & observeOn - Put subscriber & publishers on different threads.
		 */
 
		// Since publisher & subscriber run on different thread than main thread, keep
		// main thread active for 100 seconds.
		Thread.sleep(100000);
	}
}
