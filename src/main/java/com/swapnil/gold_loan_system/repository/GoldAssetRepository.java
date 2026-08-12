package com.swapnil.gold_loan_system.repository;

import com.swapnil.gold_loan_system.entity.GoldAsset;
import org.springframework.data.jpa.repository.JpaRepository;


//handles gold_assest's table operation in the database
public interface GoldAssetRepository extends JpaRepository<GoldAsset, Long> {


}
