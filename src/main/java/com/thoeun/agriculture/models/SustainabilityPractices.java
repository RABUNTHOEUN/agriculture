package com.thoeun.agriculture.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sustainability_practices")
public class SustainabilityPractices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long practiceId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    private String practiceName;
    private LocalDate implementationDate;
    private Boolean certificationReceived = false;

    // Getters and Setters
}
