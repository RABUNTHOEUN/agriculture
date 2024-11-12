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
@Table(name = "crop")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cropId;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    @JsonBackReference
    private Field field;

    private String cropName;
    private LocalDate plantingDate;
    private LocalDate harvestDate;
    private BigDecimal expectedYield;
    private BigDecimal actualYield;

    // Getters and Setters
}
