package com.siemens.customerapi.controllers;

import com.siemens.customerapi.dtos.ResponseWrapper;
import com.siemens.customerapi.models.Customer;
import com.siemens.customerapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers") //plural naming convention
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //add the customer

    @PostMapping("/v1.0")
    public ResponseEntity<ResponseWrapper> addCustomer(@RequestBody Customer customer){

        Customer customerInstance= this.customerService.addCustomer(customer);
        if (customerInstance!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper(customerInstance));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Data"));
    }




}
