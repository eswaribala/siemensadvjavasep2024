package com.siemens.dao;

import com.github.javafaker.Faker;
import com.siemens.models.Transaction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TransactionGenerator{


    public static List<Transaction> createTransactionInstances() {
        List<Transaction> transactionList=new ArrayList<>();
        Faker faker=new Faker();
        for(int i=0;i<100;i++){
            transactionList.add(new Transaction(faker.number().numberBetween(1,100000),
                    Double.parseDouble(faker.commerce().price(1000,10000)),
                    LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault())));
        }
        return transactionList;
    }
}
