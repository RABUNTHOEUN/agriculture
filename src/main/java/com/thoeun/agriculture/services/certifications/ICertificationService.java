package com.thoeun.agriculture.services.certifications;

import com.thoeun.agriculture.models.Certifications;

import java.util.List;

public interface ICertificationService {
    Certifications createCertification(Certifications certifications);
    Certifications getCertificationById(Long certificationId);
    List<Certifications> getAllCertifications();
    Certifications updateCertification(Long certificationId, Certifications certifications);
    void deleteCertification(Long certificationId);
}
