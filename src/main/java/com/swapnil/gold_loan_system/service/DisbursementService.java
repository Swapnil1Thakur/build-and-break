package com.swapnil.gold_loan_system.service;

import com.swapnil.gold_loan_system.entity.Disbursement;
import com.swapnil.gold_loan_system.entity.Loan;
import com.swapnil.gold_loan_system.enums.DisbursementStatus;
import com.swapnil.gold_loan_system.enums.LoanStatus;
import com.swapnil.gold_loan_system.repository.DisbursementRepository;
import com.swapnil.gold_loan_system.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class DisbursementService {

    private final DisbursementRepository disbursementRepository;
    private final LoanRepository loanRepository;

    public DisbursementService(DisbursementRepository disbursementRepository, LoanRepository loanRepository) {
        this.disbursementRepository = disbursementRepository;
        this.loanRepository = loanRepository;
    }

    //disburse the loan method
    public Disbursement disburseloan(Long loanId){
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if(loan.getStatus() != LoanStatus.APPROVED){
            throw new RuntimeException("Only approved loans can be disbursed");
        }


        Disbursement disbursement = new Disbursement();
        disbursement.setLoan(loan);
        disbursement.setAmount(loan.getRequestedAmount());
        disbursement.setStatus(DisbursementStatus.SUCCESS);
        disbursement.setTransactionReference("TXN: " + System.currentTimeMillis());

        loan.setStatus(LoanStatus.DISBURSED);
        loanRepository.save(loan);

        return disbursementRepository.save(disbursement);


    }
}
//APPROVED Loan -> Disbursement -> SUCCESS -> Loan = DISBURSED
