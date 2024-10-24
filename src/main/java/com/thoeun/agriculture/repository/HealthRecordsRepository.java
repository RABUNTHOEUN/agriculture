package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.HealthRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordsRepository extends JpaRepository<HealthRecords, Long> {
}
