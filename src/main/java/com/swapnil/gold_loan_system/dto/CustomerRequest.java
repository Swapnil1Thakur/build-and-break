package com.swapnil.gold_loan_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String mobileNumber;
    private String email;
    private String panNumber;
    private String aadhaarNumber;
}
