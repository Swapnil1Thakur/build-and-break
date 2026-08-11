package com.swapnil.gold_loan_system.entity;

import com.swapnil.gold_loan_system.enums.KycStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mobileNumber;

    private String email;

    private String panNumber;

    private String aadharNumber;

    @Enumerated(EnumType.STRING)
    private KycStatus kycStatus;




}
