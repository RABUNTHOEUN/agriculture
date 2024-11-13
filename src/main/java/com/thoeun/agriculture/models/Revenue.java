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
@Table(name = "revenue")
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revenueId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonBackReference
    private Farm farm;

    @NotNull(message = "Product name is required")
    private String productName;

    @NotNull(message = "Revenue amount is required")
    @Positive(message = "Revenue amount must be positive")
    private BigDecimal revenueAmount;

    @NotNull(message = "Revenue date is required")
    @PastOrPresent(message = "Revenue date cannot be in the future")
    private LocalDate revenueDate;

    // Getters and Setters
}
