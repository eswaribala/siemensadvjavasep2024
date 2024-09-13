package com.siemens;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

public class ObservableFromAction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Action action = () -> System.out.println("Hello World!");

		Completable completable = Completable.fromAction(action);

		completable.subscribe(() -> System.out.println("Done"), error -> error.printStackTrace());

	}

}
