package com.siemens;

import java.util.concurrent.CompletableFuture;

import io.reactivex.Observable;
import reactor.core.publisher.Flux;


public class ObservableFromReactiveType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Flux<Integer> reactorFlux = Flux.fromCompletionStage(CompletableFuture.<Integer>completedFuture(1));

		Observable<Integer> observable = Observable.fromPublisher(reactorFlux);

		observable.subscribe(
		    item -> System.out.println(item), 
		    error -> error.printStackTrace(),
		    () -> System.out.println("Done"));
		*/    
	}

}
