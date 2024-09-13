package com.rps.banking.subscribers;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import com.rps.banking.models.Employee;

public class EmployeeSubscriber implements Subscriber<Employee>{

	private Subscription subscription;
	
	private int counter = 0;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		// TODO Auto-generated method stub
		System.out.println("Subscribed");
		this.subscription = subscription;
		this.subscription.request(1); //requesting data from publisher
		System.out.println("onSubscribe requested 1 item");
	}

	@Override
	public void onNext(Employee item) {
		// TODO Auto-generated method stub
		System.out.println("Processing Employee "+item);
		/*b
		 * Cancel subscription
		 * System.out.println("Processing Employee "+item);
	counter++;
	if(counter==3) {
		this.subscription.cancel();
		return;
	}
	this.subscription.request(1);
		 */
		counter++;
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		System.out.println("Some error happened");
		t.printStackTrace();
	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		System.out.println("All Processing Done");
	}
	
	public int getCounter() {
		return counter;
	}

}
