package com.swapnil.gold_loan_system.controller;

import com.swapnil.gold_loan_system.entity.Loan;
import com.swapnil.gold_loan_system.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{loanId}/approve")
    public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId) {

        return ResponseEntity.ok(
                loanService.approveLoan(loanId)
        );
    }
}
