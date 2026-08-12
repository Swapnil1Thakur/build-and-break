package com.swapnil.gold_loan_system.controller;


import com.swapnil.gold_loan_system.entity.Disbursement;
import com.swapnil.gold_loan_system.service.DisbursementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/disbursements")
public class DisbursementController {
    private final DisbursementService disbursementService;

    public DisbursementController(DisbursementService disbursementService) {
        this.disbursementService = disbursementService;
    }

    @PostMapping("/loan/{loanId}")
    public ResponseEntity<Disbursement> disburseLoan(
            @PathVariable Long loanId) {

        return ResponseEntity.ok(
                disbursementService.disburseloan(loanId)
        );
    }



}
