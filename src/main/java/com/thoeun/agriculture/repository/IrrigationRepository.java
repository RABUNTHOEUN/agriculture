package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Irrigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrigationRepository extends JpaRepository<Irrigation, Long> {
}
