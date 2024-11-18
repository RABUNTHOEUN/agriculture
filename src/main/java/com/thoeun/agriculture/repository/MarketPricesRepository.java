package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.MarketPrices;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketPricesRepository extends JpaRepository<MarketPrices, Long> {
    boolean existsByProductName(String productName);
}
