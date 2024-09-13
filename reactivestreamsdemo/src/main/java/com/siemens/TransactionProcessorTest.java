package com.siemens;

import com.siemens.dao.DirectDebitTransactionSubscriber;
import com.siemens.dao.TransactionGenerator;
import com.siemens.dao.TransactionProcessor;
import com.siemens.models.DirectDebitTransaction;
import com.siemens.models.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class TransactionProcessorTest {

    public static void main(String[] args){
        // Create End Publisher
        SubmissionPublisher<Transaction> publisher = new SubmissionPublisher<>();



        // Create Processor
        TransactionProcessor transformProcessor = new TransactionProcessor(transaction ->
                DirectDebitTransaction.builder().transactionId(transaction.getTransactionId())
                        .amount(transaction.getAmount())
                        .dot(transaction.getDot())
                        .paymentDate(LocalDate.now()).build()
        );

        //Create End Subscriber
        DirectDebitTransactionSubscriber subs = new   DirectDebitTransactionSubscriber();

        //Create chain of publisher, processor and subscriber
        publisher.subscribe(transformProcessor); // publisher to processor
        transformProcessor.subscribe(subs); // processor to subscriber

        List<Transaction> emps = TransactionGenerator.createTransactionInstances();

        // Publish items
        System.out.println("Publishing Items to Subscriber");
        emps.forEach(publisher::submit);

        // Logic to wait for messages processing to finish
        while (emps.size() != subs.getCounter()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Closing publishers
        publisher.close();
        transformProcessor.close();

        System.out.println("Exiting the app");
    }


}
