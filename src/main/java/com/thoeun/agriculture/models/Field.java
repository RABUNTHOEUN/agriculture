package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "field")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonBackReference
    private Farm farm;

    @NotNull(message = "Field name is required")
    @Size(min = 3, max = 50, message = "Field name must be between 3 and 50 characters")
    private String fieldName;

    @DecimalMin(value = "0.0", inclusive = false, message = "Size must be a positive number")
    private BigDecimal size; // Size in acres or hectares

    @Size(max = 50, message = "Crop type must be a maximum of 50 characters")
    private String cropType;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Crop> crops = new ArrayList<>();

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Soil> soilTests = new ArrayList<>();

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Irrigation> irrigations = new ArrayList<>();

    // Additional Getters and Setters if needed
}
