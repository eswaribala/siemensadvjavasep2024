package com.siemens.customerapi.services;

import com.siemens.customerapi.models.Customer;
import com.siemens.customerapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        if(customer.getContactNo()>0)
            return this.customerRepository.save(customer);
        else
            return null;
    }

    @Override
    public List<Customer> getAllCustomers() {

        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long accountNo) {

        return this.customerRepository.findById(accountNo).orElse(null);
    }

    @Override
    public Customer updateCustomer(long accountNo, String email, long contactNo) {
        Customer customer=getCustomerById(accountNo);
        if(customer!=null){
            customer.setContactNo(contactNo);
            customer.setEmail(email);
            return this.customerRepository.save(customer);
        }else
          return null;
    }

    @Override
    public boolean deleteCustomer(long accountNo) {
        boolean status=false;
        Customer customer=getCustomerById(accountNo);
        if(customer!=null){
            this.customerRepository.deleteById(accountNo);
            status=true;
        }
        return status;
    }

    @Override
    public List<Customer> getCustomerByContactNo(long contactNo) {
        return null;
    }
}
