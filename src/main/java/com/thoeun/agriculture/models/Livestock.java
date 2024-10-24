package com.thoeun.agriculture.models;
import jakarta.persistence.*;
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
    private Farm farm;

    private String animalType;
    private String breed;
    private LocalDate dateOfBirth;
    private String healthStatus;
    private LocalDate vaccinationDate;

    @OneToMany(mappedBy = "livestock", cascade = CascadeType.ALL)
    private List<HealthRecords> healthRecords = new ArrayList<>();

    // Getters and Setters
}

