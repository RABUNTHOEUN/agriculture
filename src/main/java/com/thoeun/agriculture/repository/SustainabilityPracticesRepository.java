package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.SustainabilityPractices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SustainabilityPracticesRepository extends JpaRepository<SustainabilityPractices, Long> {
}
