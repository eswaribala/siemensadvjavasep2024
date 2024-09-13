package com.rps.banking.utility;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import com.rps.banking.helpers.EmployeeHelper;

import com.rps.banking.models.Employee;
import com.rps.banking.models.Freelancer;
import com.rps.banking.processor.EmployeeProcessor;
import com.rps.banking.subscribers.FreelancerSubscriber;


public class ReactiveAppWithProcessor {

	public static void main(String[] args) throws InterruptedException {
		// Create End Publisher
		SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

		// Create Processor
		EmployeeProcessor transformProcessor = new EmployeeProcessor(s -> {
			return new Freelancer(s.getId(), s.getId() + 100, s.getName());
		});

		//Create End Subscriber
		FreelancerSubscriber subs = new FreelancerSubscriber();

		//Create chain of publisher, processor and subscriber
		publisher.subscribe(transformProcessor); // publisher to processor
		transformProcessor.subscribe(subs); // processor to subscriber

		List<Employee> emps = EmployeeHelper.getEmps();

		// Publish items
		System.out.println("Publishing Items to Subscriber");
		emps.stream().forEach(i -> publisher.submit(i));

		// Logic to wait for messages processing to finish
		while (emps.size() != subs.getCounter()) {
			Thread.sleep(10);
		}

		// Closing publishers
		publisher.close();
		transformProcessor.close();

		System.out.println("Exiting the app");
	}

}