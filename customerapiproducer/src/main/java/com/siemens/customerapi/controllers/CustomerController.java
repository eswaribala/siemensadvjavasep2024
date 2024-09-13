package com.siemens.customerapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.siemens.customerapi.dtos.ResponseWrapper;
import com.siemens.customerapi.dtos.UpdateRequest;
import com.siemens.customerapi.models.Customer;
import com.siemens.customerapi.services.CustomerKafkaPublisher;
import com.siemens.customerapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("customers") //plural naming convention
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerKafkaPublisher customerKafkaPublisher;

    //add the customer

    @PostMapping("/v1.0")
    @CrossOrigin("*")
    public ResponseEntity<ResponseWrapper> addCustomer(@RequestBody Customer customer){

        Customer customerInstance= this.customerService.addCustomer(customer);
        if (customerInstance!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper(customerInstance));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Data"));
    }

    //retrieve all customers
    @GetMapping("/v1.0")
    @CrossOrigin("*")
    public List<Customer> fetchAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    //get customer by account no

    @GetMapping("/v1.0/{accountNo}")
    @CrossOrigin("*")
    public ResponseEntity<ResponseWrapper> getCustomerByAccountNo(@PathVariable("accountNo") long accountNo){

        Customer customerInstance= this.customerService.getCustomerById(accountNo);
        if (customerInstance!=null)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(customerInstance));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Account No"));

    }

    @GetMapping("/v1.0/kafka/{accountNo}")
    @CrossOrigin("*")
    public ResponseEntity<ResponseWrapper> publishCustomerInfo(@PathVariable("accountNo") long accountNo) throws JsonProcessingException {

        Customer customerInstance= this.customerService.getCustomerById(accountNo);
        if (customerInstance!=null) {
            customerKafkaPublisher.publishMessage(customerInstance);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Customer Info published"+ LocalDateTime.now()));
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Kafka Message not published..."));

    }

    @GetMapping("/v1.0/filter/{contactNo}")
    @CrossOrigin("*")
    public ResponseEntity<ResponseWrapper> getCustomerByContactNo(@PathVariable("contactNo") long contactNo){

        List<Customer> customerInstances= this.customerService.getCustomerByContactNo(contactNo);
        if (customerInstances.size()>0)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(customerInstances));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Contact No"));

    }

/*
    @PutMapping("/v1.0/{accountNo}")
    @CrossOrigin("*")
    public ResponseEntity<ResponseWrapper> updateCustomerByAccountNo(@PathVariable("accountNo")
                                                                         long accountNo, @RequestParam("email") String email, @RequestParam("contactNo") long contactNo){

        Customer customerInstance= this.customerService.updateCustomer(accountNo,email,contactNo);
        if (customerInstance!=null)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(customerInstance));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Account No"));

    }

 */
@PutMapping("/v1.0")
@CrossOrigin("*")
public ResponseEntity<ResponseWrapper> updateCustomerByAccountNo(@Valid @RequestBody UpdateRequest updateRequest){

    Customer customerInstance= this.customerService.updateCustomer(updateRequest.getAccountNo(),updateRequest.getEmail(),updateRequest.getContactNo());
    if (customerInstance!=null)
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(customerInstance));
    else
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Account No"));

}

    @DeleteMapping("/v1.0/{accountNo}")
    @CrossOrigin("*")
    public ResponseEntity<String> deleteCustomerByAccountNo(@PathVariable("accountNo") long accountNo){

        if (this.customerService.deleteCustomer(accountNo))
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Customer with Account No="+accountNo+"Deleted").getMessage());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Account No").getMessage());

    }


}
