package com.swapnil.gold_loan_system.service;

import com.swapnil.gold_loan_system.entity.Customer;
import com.swapnil.gold_loan_system.entity.GoldAsset;
import com.swapnil.gold_loan_system.repository.CustomerRepository;
import com.swapnil.gold_loan_system.repository.GoldAssetRepository;
import org.springframework.stereotype.Service;

//gold asset business logic
//Formula : (Weight × Gold Rate) = Gold Value
@Service
public class GoldAssetService {
    private final GoldAssetRepository goldAssetRepository;
    private final CustomerRepository customerRepository;

    public GoldAssetService(GoldAssetRepository goldAssetRepository, CustomerRepository customerRepository) {
        this.goldAssetRepository = goldAssetRepository;
        this.customerRepository = customerRepository;
    }


    //controller -> GoldAssetService -> Calculate Gold Value -> Attach Customer -> GoldAssetRepository -> MySql

    // Add gold asset for a customer
    // Find the customer, calculate the gold value,
    // connect the gold asset with the customer,
    // and save it in the database.
    public GoldAsset addGold(Long customerId, GoldAsset goldAsset){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        double goldValue = goldAsset.getWeightInGrams() * goldAsset.getGoldRate();

        goldAsset.setGoldValue(goldValue);
        goldAsset.setCustomer(customer);

        return goldAssetRepository.save(goldAsset);
    }
}
