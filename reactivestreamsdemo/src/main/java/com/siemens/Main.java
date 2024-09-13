package com.siemens;

import com.siemens.dao.TransactionGenerator;

import com.siemens.dao.TransactionSubscriber;
import com.siemens.models.Transaction;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class Main {
    public static void main(String[] args) {

        // Create Publisher
        SubmissionPublisher<Transaction> publisher = new SubmissionPublisher<>();

        // Register Subscriber
        TransactionSubscriber subs = new TransactionSubscriber();
          publisher.subscribe(subs);

        List<Transaction> transactionList = TransactionGenerator.createTransactionInstances();
        System.out.println(transactionList.size());
        // Publish items
        System.out.println("Publishing Items to Subscriber");
        transactionList.forEach(publisher::submit);

        // logic to wait till processing of all messages are over
        while (transactionList.size() != subs.getCounter()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // close the Publisher
        publisher.close();

        System.out.println("Exiting the app");
    }
}