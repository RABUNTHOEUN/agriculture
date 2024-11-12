package com.thoeun.agriculture.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "farm")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmId;

    private String name;
    private String location;
    private String ownerName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Field> fields = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Livestock> livestock = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Inventory> inventoryList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Sales> salesList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Expenses> expensesList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Revenue> revenueList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SustainabilityPractices> sustainabilityPracticesList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Certifications> certificationsList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WeatherData> weatherDataList = new ArrayList<>();


    // Getters and Setters

}
