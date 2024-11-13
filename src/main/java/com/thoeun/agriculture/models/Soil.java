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
@Table(name = "soil")
public class Soil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long soilId;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    @JsonBackReference
    private Field field;

    @NotNull(message = "pH value is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "pH value must be a positive number")
    @DecimalMax(value = "14.0", inclusive = true, message = "pH value cannot exceed 14")
    private BigDecimal pH;

    @NotNull(message = "Nitrogen level is required")
    @Positive(message = "Nitrogen level must be a positive number")
    private BigDecimal nitrogenLevel;

    @NotNull(message = "Phosphorus level is required")
    @Positive(message = "Phosphorus level must be a positive number")
    private BigDecimal phosphorusLevel;

    @NotNull(message = "Potassium level is required")
    @Positive(message = "Potassium level must be a positive number")
    private BigDecimal potassiumLevel;

    @NotNull(message = "Moisture level is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Moisture level must be a positive number")
    @DecimalMax(value = "100.0", inclusive = true, message = "Moisture level cannot exceed 100")
    private BigDecimal moistureLevel;

    @NotNull(message = "Test date is required")
    @PastOrPresent(message = "Test date cannot be in the future")
    private LocalDate testDate;

    // Getters and Setters
}
