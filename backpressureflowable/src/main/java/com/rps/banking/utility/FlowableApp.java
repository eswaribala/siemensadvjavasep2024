package com.rps.banking.utility;

import com.rps.banking.producers.ProducerFlowable;
import com.rps.banking.subscribers.ConsumerFlowable;

import io.reactivex.schedulers.Schedulers;




public class FlowableApp {
/*
 * the producer only gets active when the internal Flowable buffer of rxjava2 needs to be refilled.
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	        final ProducerFlowable testProducer = new ProducerFlowable(1, 1_000);
	        final ConsumerFlowable testConsumer = new ConsumerFlowable();

	        testProducer
	                .subscribeOn(Schedulers.computation())
	                .observeOn(Schedulers.single())
	                .blockingSubscribe(testConsumer);

	    } catch (Throwable t) {
	        t.printStackTrace();
	    }
	}

}
