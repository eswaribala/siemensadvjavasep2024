package com.siemens.utils;

import com.siemens.models.Transaction;
import com.siemens.models.TransactionAggregation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomCollectorDemo {
    public static void main(String[] args) {

        TransactionAggregation transactionAggregation = getListOfTransactions().stream()
                .collect(new TransactionAggregationCollector());
      //  System.out.println(transactionAggregation.getMax()+","+transactionAggregation.getAverage()+","+transactionAggregation.getTotal());

        System.out.println(transactionAggregation.getTotal());
    }

    public static List<Transaction> getListOfTransactions(){
        List<Transaction> list = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setId(1L);
        t1.setUserId(1L);
        t1.setAmount(new BigDecimal(100));
        t1.setDateTime(LocalDate.now());
        list.add(t1);

        Transaction t2 = new Transaction();
        t2.setId(2L);
        t2.setUserId(1L);
        t2.setAmount(new BigDecimal(50));
        t2.setDateTime(LocalDate.now());
        list.add(t2);

        Transaction t3 = new Transaction();
        t3.setId(3L);
        t3.setUserId(1L);
        t3.setAmount(new BigDecimal(120));
        t3.setDateTime(LocalDate.now());
        list.add(t3);

        Transaction t4 = new Transaction();
        t4.setId(4L);
        t4.setUserId(1L);
        t4.setAmount(new BigDecimal(70));
        t4.setDateTime(LocalDate.now());
        list.add(t4);

        return list;
    }
}
