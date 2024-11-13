package com.thoeun.agriculture.models;

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
@Table(name = "market_prices")
public class MarketPrices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marketPriceId;

    @NotNull(message = "Product name is required")
    private String productName;

    @NotNull(message = "Market location is required")
    private String marketLocation;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Date recorded is required")
    @PastOrPresent(message = "Date recorded cannot be in the future")
    private LocalDate dateRecorded;

    // Getters and Setters
}
