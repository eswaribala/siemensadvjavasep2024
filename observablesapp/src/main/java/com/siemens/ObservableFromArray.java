package com.siemens;

import io.reactivex.Observable;

public class ObservableFromArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] array = new Integer[10];
		for (int i = 0; i < array.length; i++) {
		    array[i] = i;
		}

		Observable<Integer> observable = Observable.fromArray(array);

		observable.subscribe(item -> System.out.println(item), error -> error.printStackTrace(), 
		     () -> System.out.println("Done"));

	}

}
