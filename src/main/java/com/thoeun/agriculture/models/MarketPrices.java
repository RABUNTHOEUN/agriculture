package com.thoeun.agriculture.models;
import jakarta.persistence.*;
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

    private String productName;
    private String marketLocation;
    private BigDecimal price;
    private LocalDate dateRecorded;

    // Getters and Setters
}
