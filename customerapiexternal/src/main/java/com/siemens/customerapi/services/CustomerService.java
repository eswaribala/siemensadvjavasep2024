package com.siemens.customerapi.services;

import com.siemens.customerapi.models.Customer;

import java.util.List;

public interface CustomerService {
    //operations
    Customer addCustomer(Customer customer);
    List<Customer> getAllCustomers();

    Customer getCustomerById(long accountNo);

    Customer updateCustomer(long accountNo, String email, long contactNo);

    boolean deleteCustomer(long accountNo);

    List<Customer> getCustomerByContactNo(long contactNo);



}
