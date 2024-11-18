package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Sales;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    boolean existsByProductName( String productName);
}
