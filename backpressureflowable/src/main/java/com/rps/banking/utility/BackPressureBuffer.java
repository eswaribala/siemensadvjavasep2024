package com.rps.banking.utility;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
/*
 * , buffer is infinite, so if published values are large in count & subscriber 
 * is too slow, then there is chance of out of memory just like Observable.


 */
public class BackPressureBuffer {
	public static void main(String[] args) throws InterruptedException {
		 
		Flowable<Object> flowableAsyncBackp = Flowable.create(emitter -> {
 
			// Publish 1000 numbers
			for (int i = 0; i < 999_000_000; i++) {
				System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
				emitter.onNext(i);
				// Thread.sleep(10);
			}
			// When all values or emitted, call complete.
			emitter.onComplete();
		}, BackpressureStrategy.BUFFER);
 
		flowableAsyncBackp.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(i -> {
			// Process received value.
			System.out.println(Thread.currentThread().getName() + " | Received = " + i);
			// 500 mills delay to simulate slow subscriber
			Thread.sleep(100);
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
		Thread.sleep(1000000);
	}
}
