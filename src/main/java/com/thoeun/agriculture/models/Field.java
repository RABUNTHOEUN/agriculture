package com.thoeun.agriculture.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "field")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    private String fieldName;
    private BigDecimal size; // in acres or hectares
    private String cropType;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Crop> crops = new ArrayList<>();

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Soil> soilTests = new ArrayList<>();

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Irrigation> irrigations = new ArrayList<>();

    // Getters and Setters
}