package com.swapnil.gold_loan_system.controller;

import com.swapnil.gold_loan_system.entity.Loan;
import com.swapnil.gold_loan_system.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<Loan> applyLoan(
            @RequestParam Long customerId,
            @RequestParam Long goldId,
            @RequestParam double requestedAmount){
        return ResponseEntity.ok(loanService.applyLoan(customerId, goldId, requestedAmount));
    }
}
