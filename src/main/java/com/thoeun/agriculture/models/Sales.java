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
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonBackReference
    private Farm farm;

    @NotNull(message = "Product name is required")
    private String productName;

    @NotNull(message = "Quantity sold is required")
    @Positive(message = "Quantity sold must be positive")
    private BigDecimal quantitySold;

    @NotNull(message = "Sale date is required")
    @PastOrPresent(message = "Sale date cannot be in the future")
    private LocalDate saleDate;

    @NotNull(message = "Revenue is required")
    @Positive(message = "Revenue must be positive")
    private BigDecimal revenue;

    @NotNull(message = "Buyer name is required")
    private String buyerName;

    // Getters and Setters
}
