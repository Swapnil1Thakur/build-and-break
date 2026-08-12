package com.swapnil.gold_loan_system.controller;

import com.swapnil.gold_loan_system.dto.CustomerRequest;
import com.swapnil.gold_loan_system.entity.Customer;
import com.swapnil.gold_loan_system.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }



    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequest request){

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setMobileNumber(request.getMobileNumber());
        customer.setEmail(request.getEmail());
        customer.setPanNumber(request.getAadhaarNumber());
        customer.setAadharNumber(request.getAadhaarNumber());

        Customer savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(savedCustomer);


    }
}
