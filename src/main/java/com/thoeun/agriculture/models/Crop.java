package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crop")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cropId;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    @JsonBackReference
    private Field field;

    @NotNull(message = "Crop name is required")
    @Size(min = 3, max = 50, message = "Crop name must be between 3 and 50 characters")
    private String cropName;

    @NotNull(message = "Planting date is required")
    @PastOrPresent(message = "Planting date cannot be in the future")
    private LocalDate plantingDate;

    private LocalDate harvestDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Expected yield must be a positive number")
    private BigDecimal expectedYield;

    @DecimalMin(value = "0.0", inclusive = false, message = "Actual yield must be a positive number")
    private BigDecimal actualYield;

    // Additional Getters and Setters if needed
}
