package com.thoeun.agriculture.models;

import jakarta.persistence.*;
import lombok.*;

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
    private Field field;

    private LocalDate irrigationDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal waterUsed; // Liters or cubic meters

    @Column(precision = 5, scale = 2)
    private BigDecimal moistureLevelBefore; // percentage

    @Column(precision = 5, scale = 2)
    private BigDecimal moistureLevelAfter; // percentage

    // Getters and Setters
}
