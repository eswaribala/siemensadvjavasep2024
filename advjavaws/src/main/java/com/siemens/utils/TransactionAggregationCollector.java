package com.siemens.utils;

import com.siemens.models.Transaction;
import com.siemens.models.TransactionAggregation;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class TransactionAggregationCollector implements Collector<Transaction, TransactionAggregation, TransactionAggregation> {
    @Override
    public Supplier<TransactionAggregation> supplier() {
        return () -> new TransactionAggregation();
    }

    @Override
    public BiConsumer<TransactionAggregation, Transaction> accumulator() {
        return (ta, t) -> {

            // Add transaction to result transaction list
          //  ta.getTransactionList().add(t);

            // Get the Max
          //  if( ta.getMax().compareTo(t.getAmount()) == -1 )
            //    ta.setMax(t.getAmount());

            // Find the latest Transaction Date
          //  if( ta.getLastTransactionDate() == null ){
            //    ta.setLastTransactionDate(t.getDateTime());
            //} else if( ta.getLastTransactionDate().compareTo(t.getDateTime()) < 0 ){
              //  ta.setLastTransactionDate(t.getDateTime());
            //}

            // Calculate Total amount
            ta.setTotal( t.getAmount().add(ta.getTotal()) );

            // Calculate Average
         //   ta.setAverage(ta.getTotal().divide(new BigDecimal(ta.getTransactionList().size())));

        };
    }

    @Override
    public BinaryOperator<TransactionAggregation> combiner() {
        /*
        return (ta1, ta2) -> {

            if( ta1.getMax().compareTo(ta2.getMax()) == -1 )
                ta1.setMax(ta2.getMax());

            ta1.setAverage( ta1.getAverage()
                    .add(ta2.getAverage())
                    .divide(new BigDecimal(2)) );

            ta1.setTotal( ta1.getTotal().add(ta2.getTotal()) );

            if( ta1.getLastTransactionDate().compareTo(ta2.getLastTransactionDate()) < 0 )
                ta1.setLastTransactionDate(ta2.getLastTransactionDate());

            return  ta1;
        };

         */
        return null;
    }

    @Override
    public Function<TransactionAggregation, TransactionAggregation> finisher() {
        return (transactionAggregation) ->{
            return transactionAggregation;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }


}