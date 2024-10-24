package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}