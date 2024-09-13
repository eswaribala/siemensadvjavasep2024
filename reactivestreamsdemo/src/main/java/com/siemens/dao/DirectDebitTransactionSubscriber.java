package com.siemens.dao;

import com.siemens.models.Transaction;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class DirectDebitTransactionSubscriber implements Subscriber<Transaction> {
    private Subscription subscription;
    private int counter = 0;
    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscribe for DirectDebitTransactionSubscriber called");
        this.subscription = subscription;
        this.subscription.request(1); //requesting data from publisher
        System.out.println("onSubscribe for DirectDebitTransactionSubscriber requested 1 transaction");
    }

    @Override
    public void onNext(Transaction transaction) {
        System.out.println("Processing Transaction " + transaction);
        counter++;
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("Some error happened");
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("All Processing Done");
    }

    public int getCounter() {
        return counter;
    }
}
