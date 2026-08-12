package com.swapnil.gold_loan_system.repository;

import com.swapnil.gold_loan_system.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
