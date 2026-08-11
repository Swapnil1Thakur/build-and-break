package com.swapnil.gold_loan_system.repository;

import com.swapnil.gold_loan_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
