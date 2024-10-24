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
@Table(name = "health_recodes")
public class HealthRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long healthRecordId;

    @ManyToOne
    @JoinColumn(name = "livestock_id", nullable = false)
    private Livestock livestock;

    private LocalDate checkupDate;
    private String diagnosis;
    private String treatment;
    private LocalDate nextCheckupDate;

    // Getters and Setters
}
