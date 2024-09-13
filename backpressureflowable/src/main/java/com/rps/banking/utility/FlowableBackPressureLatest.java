package com.rps.banking.utility;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
/*
 *  you can see subscriber directly received 923 after 127. 
 *  This means that after 127 (default buffer of 128), all values were replaced with latest 
 *  & finally last values of 923 & above remained in buffer & received by subscriber.


 */
public class FlowableBackPressureLatest {

	public static void main(String[] args) throws InterruptedException {
 
		Flowable<Object> flowableAsyncBackp = Flowable.create(emitter -> {
 
			// Publish 1000 numbers
			for (int i = 0; i < 1000; i++) {
				System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
				emitter.onNext(i);
				Thread.sleep(10);
			}
			// When all values or emitted, call complete.
			emitter.onComplete();
 
		}, BackpressureStrategy.LATEST);
 
		flowableAsyncBackp.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.single()).subscribe(i -> {
			// Process received value.
			System.out.println(Thread.currentThread().getName() + " | Received = " + i);
			// 100 mills delay to simulate slow subscriber
			Thread.sleep(100);
		}, e -> {
			// Process error
			System.err.println(Thread.currentThread().getName() + " | Error = " + e.getMessage());
		});
		/*
		 * Notice above -
		 * 
		 * BackpressureStrategy.LATEST - Overwrites values if subscriber can't keep up.
		 * 
		 * subscribeOn & observeOn - Put subscriber & publishers on different threads.
		 */
 
		// Since publisher & subscriber run on different thread than main thread, keep
		// main thread active for 100 seconds.
		Thread.sleep(100000);
	}

}
