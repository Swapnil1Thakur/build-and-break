package com.swapnil.gold_loan_system.entity;

import com.swapnil.gold_loan_system.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loans")
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private double requestedAmount;

    private double eligibleAmount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @ManyToOne   //many loans can belong to one customer
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne      //many loans can reference one gold asset
    @JoinColumn(name = "gold_asset_id")
    private GoldAsset goldAsset;


}
