package com.siemens.customerapi.controllers;

import com.siemens.customerapi.dtos.ResponseWrapper;
import com.siemens.customerapi.dtos.UpdateRequest;
import com.siemens.customerapi.models.Customer;
import com.siemens.customerapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers") //plural naming convention
public class CustomerController {

    @Autowired
    private CustomerService customerService;

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
@PutMapping("/v1.0}")
@CrossOrigin("*")
public ResponseEntity<ResponseWrapper> updateCustomerByAccountNo(@RequestBody UpdateRequest updateRequest){

    Customer customerInstance= this.customerService.updateCustomer(updateRequest.getAccountNo(),updateRequest.getEmail(),updateRequest.getContactNo());
    if (customerInstance!=null)
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(customerInstance));
    else
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Account No"));

}

    @DeleteMapping("/v1.0/{accountNo}")
    @CrossOrigin("*")
    public ResponseEntity<ResponseWrapper> deleteCustomerByAccountNo(@PathVariable("accountNo") long accountNo){

        if (this.customerService.deleteCustomer(accountNo))
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Customer with Account No="+accountNo+"Deleted"));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid Account No"));

    }


}
