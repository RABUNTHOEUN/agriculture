package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Certifications;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import com.thoeun.agriculture.services.certifications.ICertificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/certifications")
@AllArgsConstructor
public class CertificationsController {

    private final ICertificationService certificationService;
    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCertification(@Valid @RequestBody Certifications certifications) {
        try {
            try {
                farmService.getFarmById(certifications.getFarm().getFarmId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Certifications createdCertification = certificationService.createCertification(certifications);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Certification created successfully", createdCertification));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCertifications () {
        try {
            List<Certifications> certifications = certificationService.getAllCertifications();
            return ResponseEntity.ok(new ApiResponse("Success", certifications));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{certificationId}")
    public ResponseEntity<ApiResponse> getCertificationById(@PathVariable Long certificationId) {
        try {
            Certifications certifications = certificationService.getCertificationById(certificationId);
            return ResponseEntity.ok(new ApiResponse("Found ", certifications));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{certificationId}")
    public ResponseEntity<ApiResponse> updateCertification(@PathVariable Long certificationId,@Valid @RequestBody Certifications certifications) {

        try {
            Certifications updatedCertification = certificationService.updateCertification(certificationId, certifications);
            return ResponseEntity.ok(new ApiResponse("Certification update success", updatedCertification));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{certificationId}")
    public ResponseEntity<ApiResponse> deleteCertification(@PathVariable Long certificationId) {
        try {
            certificationService.deleteCertification(certificationId);
            return ResponseEntity.ok(new ApiResponse("Certification deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
