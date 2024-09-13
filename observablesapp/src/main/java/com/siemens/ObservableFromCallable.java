package com.siemens;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class ObservableFromCallable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Callable<String> callable = () -> {
		    System.out.println("Rocking....!");
		    return "Rocking....!";
		};

		Observable<String> observable = Observable.fromCallable(callable);

		observable.subscribe(item -> System.out.println(item), error -> error.printStackTrace(), 
		    () -> System.out.println("Done"));
	}

}
