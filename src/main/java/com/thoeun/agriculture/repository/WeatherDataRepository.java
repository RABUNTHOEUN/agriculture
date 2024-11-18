package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.WeatherData;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    boolean existsByTemperature( BigDecimal temperature);
}
