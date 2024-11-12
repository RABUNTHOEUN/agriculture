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
@Table(name = "soil")
public class Soil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long soilId;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    @JsonBackReference
    private Field field;

    private BigDecimal pH;
    private BigDecimal nitrogenLevel;
    private BigDecimal phosphorusLevel;
    private BigDecimal potassiumLevel;
    private BigDecimal moistureLevel;
    private LocalDate testDate;

    // Getters and Setters
}
