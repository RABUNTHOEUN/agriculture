package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "livestock")
public class Livestock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long livestockId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonBackReference
    private Farm farm;

    @NotNull(message = "Animal type is required")
    private String animalType;

    @NotNull(message = "Breed is required")
    private String breed;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Health status is required")
    private String healthStatus;

    @NotNull(message = "Vaccination date is required")
    @PastOrPresent(message = "Vaccination date cannot be in the future")
    private LocalDate vaccinationDate;

    @OneToMany(mappedBy = "livestock", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HealthRecords> healthRecords = new ArrayList<>();

    // Getters and Setters
}
