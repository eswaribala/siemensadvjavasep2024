package com.siemens;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ObservablePubSubtransformation {
    public static void main(String[] args){
        Observable<String> observable = Observable.just("R", "x", "J", "a", "v", "a");
        Consumer<? super String> consumer = System.out::println;
        //Transformation using map() method
        observable.map(w -> w.toUpperCase()).subscribe(consumer);

        //change data type of the event

        Consumer<? super Integer> consumer1 = System.out::println;

        observable.map(w -> w.toUpperCase().hashCode()).subscribe(consumer1);
    }
}
