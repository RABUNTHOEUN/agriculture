package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Livestock;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivestockRepository extends JpaRepository<Livestock, Long> {

    boolean existsByanimalType(String animalType);
}

