package com.swapnil.gold_loan_system.controller;

import com.swapnil.gold_loan_system.entity.Customer;
import com.swapnil.gold_loan_system.entity.GoldAsset;
import com.swapnil.gold_loan_system.service.GoldAssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gold")
public class GoldAssetController {
    private final GoldAssetService goldAssetService;

    public GoldAssetController(GoldAssetService goldAssetService) {
        this.goldAssetService = goldAssetService;
    }

    // Add gold for a specific customer.
    // Get the customer ID, receive gold details,
    // and send them to the service to save the gold asset.
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<GoldAsset> addGold(
            @PathVariable Long customerId,
            @RequestBody GoldAsset goldAsset){

            return ResponseEntity.ok(goldAssetService.addGold(customerId, goldAsset));
    }

    @GetMapping("/{goldId}/eligibility")
    public ResponseEntity<Double> calculateEligibility(@PathVariable Long goldId){
        return ResponseEntity.ok(
                goldAssetService.calculateEligibleAmount(goldId));

    }

}
