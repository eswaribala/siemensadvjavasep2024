package com.siemens;

import io.reactivex.Completable;

public class ObservableFromRunnable {
	
	public static void main(String[] args) {
		Runnable runnable = () -> System.out.println("Happy Learning!");

		Completable completable = Completable.fromRunnable(runnable);

		completable.subscribe(() -> System.out.println("Done"), error -> error.printStackTrace());
	}

}
