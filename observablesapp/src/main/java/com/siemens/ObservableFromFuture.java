package com.siemens;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class ObservableFromFuture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

		Future<String> future = executor.schedule(() -> "Rocking..!", 1, TimeUnit.SECONDS);

		Observable<String> observable = Observable.fromFuture(future);

		observable.subscribe(
		    item -> System.out.println(item), 
		    error -> error.printStackTrace(),
		    () -> System.out.println("Done"));

		executor.shutdown();
	}

}
