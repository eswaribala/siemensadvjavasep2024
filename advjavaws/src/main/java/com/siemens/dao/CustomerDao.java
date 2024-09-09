package com.siemens.dao;

import com.siemens.models.Customer;
import com.siemens.models.Product;
import com.siemens.models.ProductV1;

import java.util.List;

public interface CustomerDao{

        Customer addCustomer(Customer customer);

        List<Customer> getAllCustomers();
}
