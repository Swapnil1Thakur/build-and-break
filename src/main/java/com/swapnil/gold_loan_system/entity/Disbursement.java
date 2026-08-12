package com.swapnil.gold_loan_system.entity;

import com.swapnil.gold_loan_system.enums.DisbursementStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "disbursements")
@Getter
@Setter
public class Disbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String transactionReference;

    @Enumerated(EnumType.STRING)
    private DisbursementStatus status;

    //One Loan → One Disbursement
    @OneToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}


// Stores loan disbursement details.
// Links the disbursement to a loan.
// Tracks the disbursement status.
//i.e Approved loan ke against kitna paisa disburse hua, transaction reference kya hai aur status kya hai