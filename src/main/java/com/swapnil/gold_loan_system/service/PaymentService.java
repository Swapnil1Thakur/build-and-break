package com.swapnil.gold_loan_system.service;

import com.swapnil.gold_loan_system.entity.Loan;
import com.swapnil.gold_loan_system.entity.Payment;
import com.swapnil.gold_loan_system.enums.LoanStatus;
import com.swapnil.gold_loan_system.enums.PaymentStatus;
import com.swapnil.gold_loan_system.repository.LoanRepository;
import com.swapnil.gold_loan_system.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final LoanRepository loanRepository;

    public PaymentService(PaymentRepository paymentRepository, LoanRepository loanRepository) {
        this.paymentRepository = paymentRepository;
        this.loanRepository = loanRepository;

    }

    //implementing retry mechanism (mock way)
    private void callPaymentGateway(int attempt){
        if(attempt < 3){
            throw new RuntimeException("Payment gateway temporarily unavailable");

        }
    }

    //This method creates a repayment/payment for a loan
    @Transactional
    public Payment makePayment(Long loadId, double amount, String idempotencyKey){
        Loan loan = loanRepository.findLoanForUpdate(loadId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if(loan.getStatus() != LoanStatus.DISBURSED){
            throw new RuntimeException("Payment allowed only for disbursed loan");
        }

        Optional<Payment> existingPayment = paymentRepository.findByIdempotencyKey(idempotencyKey);

        if(existingPayment.isPresent()){
            return existingPayment.get();
        }

        int maxAttempts = 3;
        for(int attempt = 1; attempt <= maxAttempts; attempt++){
            try{
                callPaymentGateway(attempt);
                break;
            }catch(RuntimeException e){
                if(attempt == maxAttempts){
                    throw new RuntimeException("Payment failed after retries");
                }

                try{
                    Thread.sleep(1000L * attempt);

                }catch(InterruptedException ex){
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted");

                }

            }
        }




        // Create a new Payment object
        Payment payment = new Payment();

        // link this payment with the loan
        payment.setIdempotencyKey(idempotencyKey);
        payment.setLoan(loan);
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId("PAY : " + System.currentTimeMillis());


        return paymentRepository.save(payment);


    }
}
