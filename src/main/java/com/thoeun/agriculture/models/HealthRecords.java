package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "health_records")
public class HealthRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long healthRecordId;

    @ManyToOne
    @JoinColumn(name = "livestock_id", nullable = false)
    @JsonBackReference
    private Livestock livestock;

    @NotNull(message = "Checkup date is required")
    @PastOrPresent(message = "Checkup date cannot be in the future")
    private LocalDate checkupDate;

    @NotNull(message = "Diagnosis is required")
    @Size(min = 3, max = 200, message = "Diagnosis must be between 3 and 200 characters")
    private String diagnosis;

    @NotNull(message = "Treatment is required")
    @Size(min = 3, max = 200, message = "Treatment must be between 3 and 200 characters")
    private String treatment;

    //    @PastOrPresent(message = "Next checkup date cannot be in the future")
    @NotNull(message = "Next checkup date is required")
    private LocalDate nextCheckupDate;

    // Additional Getters and Setters if needed
}
