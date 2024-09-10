package com.siemens.customerapi.services;

import com.siemens.customerapi.models.Customer;
import com.siemens.customerapi.repositories.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EntityManager entityManager;

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
      //  this.customerRepository.findByContactNo(contactNo);

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq= criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root=cq.from(Customer.class);
        cq.where(criteriaBuilder.equal(root.get("contactNo"), contactNo));
        CriteriaQuery<Customer> result=cq.select(root);
        TypedQuery<Customer> typedQuery= entityManager.createQuery(result);
        return typedQuery.getResultList();

    }
}
