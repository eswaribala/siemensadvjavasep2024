package com.rps.banking.utility;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import com.rps.banking.helpers.EmployeeHelper;
import com.rps.banking.models.Employee;
import com.rps.banking.subscribers.EmployeeSubscriber;

public class ReactiveApp {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// Create Publisher
				SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

				// Register Subscriber
				EmployeeSubscriber subs = new EmployeeSubscriber();
				publisher.subscribe(subs);
				
				List<Employee> emps = EmployeeHelper.getEmps();

				// Publish items
				System.out.println("Publishing Items to Subscriber");
				emps.stream().forEach(i -> publisher.submit(i));

				// logic to wait till processing of all messages are over
				while (emps.size() != subs.getCounter()) {
					Thread.sleep(10);
				}
				// close the Publisher
				publisher.close();

				System.out.println("Exiting the app");
	}

}
