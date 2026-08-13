package com.swapnil.gold_loan_system.service;

import com.swapnil.gold_loan_system.entity.Loan;
import com.swapnil.gold_loan_system.entity.Payment;
import com.swapnil.gold_loan_system.enums.LoanStatus;
import com.swapnil.gold_loan_system.enums.PaymentStatus;
import com.swapnil.gold_loan_system.repository.LoanRepository;
import com.swapnil.gold_loan_system.repository.PaymentRepository;

public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final LoanRepository loanRepository;

    public PaymentService(PaymentRepository paymentRepository, LoanRepository loanRepository) {
        this.paymentRepository = paymentRepository;
        this.loanRepository = loanRepository;
    }

    //This method creates a repayment/payment for a loan
    public Payment makePayment(Long loadId, double amount){
        Loan loan = loanRepository.findById(loadId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if(loan.getStatus() != LoanStatus.DISBURSED){
            throw new RuntimeException("Payment allowed only for disbursed loan");
        }

        // Create a new Payment object
        Payment payment = new Payment();

        // link this payment with the loan
        payment.setLoan(loan);
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId("PAY : " + System.currentTimeMillis());

        return paymentRepository.save(payment);


    }
}
