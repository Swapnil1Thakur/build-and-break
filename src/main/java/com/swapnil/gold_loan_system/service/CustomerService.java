package com.swapnil.gold_loan_system.service;

import com.swapnil.gold_loan_system.entity.Customer;
import com.swapnil.gold_loan_system.enums.KycStatus;
import com.swapnil.gold_loan_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    //new customer created -> but kyc is pending
    public Customer createCustomer(Customer customer){
        customer.setKycStatus(KycStatus.PENDING);
        return customerRepository.save(customer);
    }

    //fetch customer based on ID
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    //verify kyc
    public Customer verifyKyc(Long customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setKycStatus(KycStatus.VERIFIED);

        return customerRepository.save(customer);
    }


}
