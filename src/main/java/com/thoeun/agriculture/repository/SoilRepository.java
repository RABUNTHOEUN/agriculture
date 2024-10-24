package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Soil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilRepository extends JpaRepository<Soil, Long> {
}
