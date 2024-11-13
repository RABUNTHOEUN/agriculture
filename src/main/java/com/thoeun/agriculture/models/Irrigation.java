package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "irrigation")
public class Irrigation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long irrigationId;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    @JsonBackReference
    private Field field;

    @NotNull(message = "Irrigation date is required")
    @PastOrPresent(message = "Irrigation date cannot be in the future")
    private LocalDate irrigationDate;

    @NotNull(message = "Water used is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Water used must be greater than zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal waterUsed; // Liters or cubic meters

    @NotNull(message = "Moisture level before is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Moisture level before must be greater than zero")
    @DecimalMin(value = "100.0", inclusive = true, message = "Moisture level before cannot exceed 100%")
    @Column(precision = 5, scale = 2)
    private BigDecimal moistureLevelBefore; // percentage

    @NotNull(message = "Moisture level after is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Moisture level after must be greater than zero")
    @DecimalMin(value = "100.0", inclusive = true, message = "Moisture level after cannot exceed 100%")
    @Column(precision = 5, scale = 2)
    private BigDecimal moistureLevelAfter; // percentage

    // Getters and Setters
}
