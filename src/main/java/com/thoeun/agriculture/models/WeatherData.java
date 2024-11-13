package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weatherId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonBackReference
    private Farm farm;

    @NotNull(message = "Temperature is required")
    @DecimalMin(value = "-100.0", message = "Temperature cannot be lower than -100°C")
    @DecimalMax(value = "100.0", message = "Temperature cannot be higher than 100°C")
    private BigDecimal temperature;

    @NotNull(message = "Humidity is required")
    @DecimalMin(value = "0.0", message = "Humidity cannot be negative")
    @DecimalMax(value = "100.0", message = "Humidity cannot be more than 100%")
    private BigDecimal humidity;

    @NotNull(message = "Rainfall is required")
    @DecimalMin(value = "0.0", message = "Rainfall cannot be negative")
    private BigDecimal rainfall;

    @NotNull(message = "Wind speed is required")
    @DecimalMin(value = "0.0", message = "Wind speed cannot be negative")
    private BigDecimal windSpeed;

    @NotNull(message = "Weather date is required")
    private LocalDate weatherDate;

    // Getters and Setters
}
