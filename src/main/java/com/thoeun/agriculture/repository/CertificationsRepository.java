package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Certifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationsRepository extends JpaRepository<Certifications, Long> {
}

