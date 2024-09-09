package com.siemens.utils;

import com.github.javafaker.Faker;
import com.siemens.dao.CustomerDao;
import com.siemens.dao.CustomerImpl;
import com.siemens.models.Customer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectAndThenDemo {
    public static void main(String[] args){
        CustomerDao customerDao=new CustomerImpl();
        addCustomers(customerDao);
        Map<LocalDate, Set<String>> customersSet=customerDao
                .getAllCustomers()
                .stream().collect(Collectors.groupingBy(c -> c.getDob(),
                        Collectors.mapping(c -> c.getName(),
                                Collectors.collectingAndThen(Collectors.toSet(),
                                        Collections::unmodifiableSet))));
        customersSet.entrySet().stream().forEach(entry->System.out.println(entry.getKey()+","+entry.getValue()));

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
