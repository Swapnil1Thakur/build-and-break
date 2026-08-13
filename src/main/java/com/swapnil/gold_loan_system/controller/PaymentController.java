package com.swapnil.gold_loan_system.controller;

import com.swapnil.gold_loan_system.entity.Payment;
import com.swapnil.gold_loan_system.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // API for customer to make a repayment against their loan
    @PostMapping("/loan/{loanId}")
    public ResponseEntity<Payment> makePayment(@PathVariable Long loanId,
                                               @RequestParam double amount,
                                               @RequestHeader("Idempotency-Key") String idempotencyKey){
        return  ResponseEntity.ok(
                paymentService.makePayment(loanId, amount, idempotencyKey));
    }
}
