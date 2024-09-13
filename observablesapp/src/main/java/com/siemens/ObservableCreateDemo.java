package com.siemens;

import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
public class ObservableCreateDemo {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

		ObservableOnSubscribe<String> handler = emitter -> {

		     Future<Object> future = executor.schedule(() -> {
		          emitter.onNext("Reactive");
		          emitter.onNext("Learning");
		          emitter.onComplete();
		          return null;
		     }, 1, TimeUnit.SECONDS);

		     emitter.setCancellable(() -> future.cancel(false));
		};

		Observable<String> observable = Observable.create(handler);

		observable.subscribe(item -> System.out.println(item), error -> error.printStackTrace(),
		     () -> System.out.println("Done"));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdown();

	}

}
