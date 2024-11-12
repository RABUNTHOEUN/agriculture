package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "certifications")
public class Certifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificationId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonBackReference
    private Farm farm;

    private String certificationName;
    private LocalDate issueDate;
    private LocalDate validUntil;

    // Getters and Setters
}
