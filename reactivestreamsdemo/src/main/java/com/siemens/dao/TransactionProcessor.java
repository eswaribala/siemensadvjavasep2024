package com.siemens.dao;

import com.siemens.models.DirectDebitTransaction;
import com.siemens.models.Transaction;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class TransactionProcessor extends SubmissionPublisher<DirectDebitTransaction>
        implements Flow.Processor<Transaction,DirectDebitTransaction> {

    private Flow.Subscription subscription;
    private Function<Transaction,DirectDebitTransaction> function;
    public TransactionProcessor(Function<Transaction,DirectDebitTransaction> function) {
        super();
        this.function = function;
    }
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Transaction item) {
        submit((DirectDebitTransaction) function.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }
}
