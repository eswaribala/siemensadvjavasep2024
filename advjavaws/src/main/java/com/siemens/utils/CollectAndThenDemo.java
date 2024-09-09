package com.siemens.utils;

import com.github.javafaker.Faker;
import com.siemens.dao.CustomerDao;
import com.siemens.dao.CustomerImpl;
import com.siemens.models.Customer;

import java.time.LocalDate;
import java.time.ZoneId;

public class CollectAndThenDemo {
    public static void main(String[] args){
        CustomerDao customerDao=new CustomerImpl();
        addCustomers(customerDao);
        customerDao.getAllCustomers().stream().forEach(System.out::println);
    }

    private static void addCustomers(CustomerDao customerDao){
        Faker faker=new Faker();
        for(int i=0;i<100;i++){
            customerDao.addCustomer(new Customer(faker.artist().name(),
                    faker.date().birthday(20, 60)
                    .toInstant().atZone(ZoneId.
                                    systemDefault()) .toLocalDate()));

        }

    }
}
