package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Livestock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivestockRepository extends JpaRepository<Livestock, Long> {
}

