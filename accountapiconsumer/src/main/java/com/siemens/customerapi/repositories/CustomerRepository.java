package com.siemens.customerapi.repositories;

import com.siemens.customerapi.dtos.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,Long> {
}
