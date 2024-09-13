package com.rps.banking.utility;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.schedulers.Schedulers;
/*
 * Subscriber received values till 314 & then onError handler
 *  was called due to MissingBackpressureException. 
 * After that subscriber stopped.
 */
public class BackPressureError {
	public static void main(String[] args) throws InterruptedException {
		 
		Flowable<Object> flowableAsyncBackp = Flowable.create(emitter -> {
 
			// Publish 1000 numbers
			for (int i = 0; i < 1000; i++) {
				System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
				// BackpressureStrategy.ERROR will cause MissingBackpressureException when
				// subscriber can't keep up. So handle exception & call error handler.
				try {
					emitter.onNext(i);
				} catch (MissingBackpressureException exception) {
					emitter.onError(exception);
				}
			}
			// When all values or emitted, call complete.
			emitter.onComplete();
 
		}, BackpressureStrategy.ERROR);
 
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
		 * BackpressureStrategy.ERROR - Throws MissingBackpressureException is
		 * subscriber can't keep up.
		 * 
		 * subscribeOn & observeOn - Put subscriber & publishers on different threads.
		 */
 
		// Since publisher & subscriber run on different thread than main thread, keep
		// main thread active for 100 seconds.
		Thread.sleep(100000);
	}
}
