package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonBackReference
    private Farm farm;

    private String productName;
    private BigDecimal quantitySold;
    private LocalDate saleDate;
    private BigDecimal revenue;
    private String buyerName;

    // Getters and Setters
}
