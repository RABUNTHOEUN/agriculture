package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    boolean existsByFarmAndFieldName(Farm farm, String fieldName);
}
