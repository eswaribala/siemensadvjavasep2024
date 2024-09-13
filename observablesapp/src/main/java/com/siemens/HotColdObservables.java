package com.siemens;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class HotColdObservables {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		/* Demonstration of a Cold Observable */
		Observable<Long> cold = Observable.interval(500, TimeUnit.MILLISECONDS); // emits a long every 500 milli seconds
		cold.subscribe(l -> System.out.println("sub1, " + l)); // subscriber1
		Thread.sleep(1000); // interval between the two subscribes
		cold.subscribe(l -> System.out.println("sub2, " + l)); // subscriber2
	System.out.println("Hot Starts....");
	//hot
		ConnectableObservable<Long> hot = Observable
                .interval(500, TimeUnit.MILLISECONDS)
                .publish(); // returns ConnectableObservable
			hot.connect(); // connect to subscribe
			
			hot.subscribe(l -> System.out.println("sub1, " + l));
			Thread.sleep(1000);
			hot.subscribe(l -> System.out.println("sub2, " + l));
	
	}

}
