package com.rps.banking.subscribers;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import com.rps.banking.models.Freelancer;


public class FreelancerSubscriber implements Subscriber<Freelancer> {

	private Subscription subscription;
	
	private int counter = 0;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		System.out.println("Subscribed for Freelancer");
		this.subscription = subscription;
		this.subscription.request(1); //requesting data from publisher
		System.out.println("onSubscribe requested 1 item for Freelancer");
	}

	@Override
	public void onNext(Freelancer item) {
		System.out.println("Processing Freelancer "+item);
		counter++;
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable e) {
		System.out.println("Some error happened in FreelancerSubscriber");
		e.printStackTrace();
	}

	@Override
	public void onComplete() {
		System.out.println("All Processing Done for FreelancerSubscriber");
	}

	public int getCounter() {
		return counter;
	}

}