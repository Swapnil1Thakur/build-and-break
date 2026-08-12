package com.swapnil.gold_loan_system.service;

import com.swapnil.gold_loan_system.entity.Customer;
import com.swapnil.gold_loan_system.entity.GoldAsset;
import com.swapnil.gold_loan_system.entity.Loan;
import com.swapnil.gold_loan_system.enums.LoanStatus;
import com.swapnil.gold_loan_system.repository.CustomerRepository;
import com.swapnil.gold_loan_system.repository.GoldAssetRepository;
import com.swapnil.gold_loan_system.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final GoldAssetRepository goldAssetRepository;

    public LoanService(LoanRepository loanRepository, CustomerRepository customerRepository, GoldAssetRepository goldAssetRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
        this.goldAssetRepository = goldAssetRepository;
    }

    // - Requested amount must be <= the eligible LTV amount.
    // - Reject the loan if the requested amount exceeds the eligible amount.
    public Loan applyLoan(Long customerId, Long goldId, double requestedAmount){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        GoldAsset goldAsset = goldAssetRepository.findById(goldId)
                .orElseThrow(() -> new RuntimeException("Gold asset not found"));

        double eligibleAmount = goldAsset.getGoldValue() * 75 / 100;

        if (requestedAmount > eligibleAmount) {
            throw new RuntimeException("Requested amount exceeds LTV eligibility");
        }

        Loan loan = new Loan();
        loan.setCustomer(customer);
        loan.setGoldAsset(goldAsset);
        loan.setRequestedAmount(requestedAmount);
        loan.setEligibleAmount(eligibleAmount);
        loan.setStatus(LoanStatus.APPLIED);

        return loanRepository.save(loan);

        //(customer + gold ) -> LTV check -> eligible -> loan create -> applied

    }
}
