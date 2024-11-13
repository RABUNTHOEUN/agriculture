package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @JsonBackReference
    private Farm farm;

    @NotNull(message = "Practice name is required")
    @Size(min = 3, max = 255, message = "Practice name must be between 3 and 255 characters")
    private String practiceName;

    @NotNull(message = "Implementation date is required")
    @PastOrPresent(message = "Implementation date cannot be in the future")
    private LocalDate implementationDate;

    private Boolean certificationReceived = false;

    // Getters and Setters
}
