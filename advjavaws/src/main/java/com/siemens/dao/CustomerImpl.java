package com.siemens.dao;

import com.siemens.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements CustomerDao{
    private List<Customer> customers;
    public CustomerImpl(){
        customers=new ArrayList<>();
    }
    @Override
    public Customer addCustomer(Customer customer) {
         customers.add(customer);
         return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }
}
