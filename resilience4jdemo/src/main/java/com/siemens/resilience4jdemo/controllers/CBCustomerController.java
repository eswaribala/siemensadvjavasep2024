package com.siemens.resilience4jdemo.controllers;

import com.siemens.resilience4jdemo.services.CBCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CBCustomerController {

    @Autowired
    private CBCustomerService cbCustomerService;

    @GetMapping("/v1.0")
    public String getCustomerData(){

        return cbCustomerService.getCustomerService();

    }


}
