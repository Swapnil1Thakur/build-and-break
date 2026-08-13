package com.swapnil.gold_loan_system.entity;


import com.swapnil.gold_loan_system.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {

    // Stores customer loan repayment details.
    // Records the payment amount and transaction ID.
    // Links the payment to the loan and tracks its status.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;



}
