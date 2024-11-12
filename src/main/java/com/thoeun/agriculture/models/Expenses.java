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
@Table(name = "expenses")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonBackReference
    private Farm farm;

    private String expenseType;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String description;

    // Getters and Setters
}
