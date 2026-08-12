package com.swapnil.gold_loan_system.controller;

import com.swapnil.gold_loan_system.dto.CustomerRequest;
import com.swapnil.gold_loan_system.entity.Customer;
import com.swapnil.gold_loan_system.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }



    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerRequest request){

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setMobileNumber(request.getMobileNumber());
        customer.setEmail(request.getEmail());
        customer.setPanNumber(request.getAadhaarNumber());
        customer.setAadharNumber(request.getAadhaarNumber());

        Customer savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(savedCustomer);


    }

    //fetch customer by id
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    //verify kyc
    @PutMapping("/{id}/verify-kyc")
    public ResponseEntity<Customer> verifyKyc(@PathVariable Long id){
        return ResponseEntity.ok(customerService.verifyKyc(id));
    }
}
