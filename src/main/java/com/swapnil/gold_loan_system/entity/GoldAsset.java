package com.swapnil.gold_loan_system.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//represents customer's pledged gold
@Entity
@Table(name = "gold_assets")
@Getter
@Setter
public class GoldAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double weightInGrams;

    private double purity;

    private double goldRate;

    private double goldValue;


    //many to one
    //1 customer -> many gold assets

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
