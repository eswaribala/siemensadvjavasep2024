package com.siemens;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ObservablePubSub {
    public static void main(String[] args){
        //producer
        Observable<String> observable = Observable.just("R", "x", "J", "a", "v", "a");

        //consumer
        Consumer<? super String> consumer = System.out::println;

        //Attaching producer to consumer
        observable.subscribe(consumer);
    }
}
