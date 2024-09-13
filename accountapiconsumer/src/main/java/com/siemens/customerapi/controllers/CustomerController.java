package com.siemens.customerapi.controllers;

import com.siemens.customerapi.dtos.ResponseWrapper;
import com.siemens.customerapi.services.CustomerKafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers") //plural naming convention
public class CustomerController {

    @Autowired
    private CustomerKafkaConsumer customerKafkaConsumer;

    @GetMapping("/v1.0/kafka")
    @CrossOrigin("*")
    public String publishCustomerInfo(){
      return "hi";

    }



}
