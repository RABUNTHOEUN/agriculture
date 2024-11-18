package com.thoeun.agriculture.services.certifications;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Certifications;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Livestock;
import com.thoeun.agriculture.repository.CertificationsRepository;
import com.thoeun.agriculture.repository.LivestockRepository;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CertificationService implements ICertificationService{

    private final CertificationsRepository certificationsRepository;
    private final IFarmService farmService;

    @Override
    public Certifications createCertification(Certifications certifications) {
        Farm farm = farmService.getFarmById(certifications.getFarm().getFarmId());
        if (farm == null) {
            throw  new ResourceNotFoundException("Farm Not Found!");
        }
        if (certificationsRepository.existsByCertificationName(certifications.getCertificationName())) {
            throw new AlreadyExistsException("Certification with the same certification name already exists in this Certification!");
        }
        return certificationsRepository.save(certifications);
    }

    @Override
    public Certifications getCertificationById(Long certificationId) {
        return certificationsRepository.findById(certificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Certification not found with ID: " + certificationId));
    }

    @Override
    public List<Certifications> getAllCertifications() {
        try {
            return certificationsRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Certifications updateCertification(Long certificationId, Certifications certifications) {
        Certifications updateCertification = certificationsRepository.findById(certificationId).orElseThrow(() -> new ResourceNotFoundException("Certification not found!"));
        updateCertification.setCertificationName(certifications.getCertificationName());
        updateCertification.setIssueDate(certifications.getIssueDate());
        updateCertification.setValidUntil(certifications.getValidUntil());
        return certificationsRepository.save(updateCertification);
    }

    @Override
    public void deleteCertification(Long certificationId) {
        certificationsRepository.findById(certificationId).ifPresentOrElse(certificationsRepository::delete, () -> {
            throw new ResourceNotFoundException("Certification Not Found!");
        });
    }
}
