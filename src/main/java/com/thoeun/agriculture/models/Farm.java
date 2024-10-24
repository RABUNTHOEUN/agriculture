package com.thoeun.agriculture.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
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
    private List<Field> fields = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Livestock> livestock = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Inventory> inventoryList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Sales> salesList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Expenses> expensesList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Revenue> revenueList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<SustainabilityPractices> sustainabilityPracticesList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Certifications> certificationsList = new ArrayList<>();

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<WeatherData> weatherDataList = new ArrayList<>();


    // Getters and Setters


    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


}
