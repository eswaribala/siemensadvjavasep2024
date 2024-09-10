package com.siemens.customerapi.repositories;

import com.siemens.customerapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByContactNo(long number);
}
