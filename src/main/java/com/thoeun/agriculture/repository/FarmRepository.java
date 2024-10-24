package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    boolean existsByName(String name);
}
