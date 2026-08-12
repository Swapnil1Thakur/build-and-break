package com.swapnil.gold_loan_system.dto;

//customer request dto
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String mobileNumber;

    @Email
    private String email;

    @NotBlank
    private String panNumber;

    @NotBlank
    private String aadhaarNumber;
}
