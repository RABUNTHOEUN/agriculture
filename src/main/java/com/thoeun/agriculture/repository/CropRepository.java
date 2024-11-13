package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Crop;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    boolean existsByCropName(String cropName);
}
