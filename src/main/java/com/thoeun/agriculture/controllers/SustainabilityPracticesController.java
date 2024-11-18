package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.SustainabilityPractices;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import com.thoeun.agriculture.services.sustainabilityPractices.ISustainabilityPracticeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/sustainabilityPractices")
@AllArgsConstructor
public class SustainabilityPracticesController {

    private final ISustainabilityPracticeService sustainabilityPracticeService;
    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createSustainabilityPractices(@Valid @RequestBody SustainabilityPractices sustainabilityPractices) {
        try {
            try {
                farmService.getFarmById(sustainabilityPractices.getFarm().getFarmId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            SustainabilityPractices createdSustainabilityPractices = sustainabilityPracticeService.createSustainabilityPractices(sustainabilityPractices);
            return ResponseEntity.status(CREATED).body(new ApiResponse("SustainabilityPractices created successfully", createdSustainabilityPractices));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllSustainabilityPractices () {
        try {
            List<SustainabilityPractices> sustainabilityPractices = sustainabilityPracticeService.getAllSustainabilityPractices();
            return ResponseEntity.ok(new ApiResponse("Success", sustainabilityPractices));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{sustainabilityPracticesId}")
    public ResponseEntity<ApiResponse> getSustainabilityPracticesById(@PathVariable Long sustainabilityPracticesId) {
        try {
            SustainabilityPractices sustainabilityPractices = sustainabilityPracticeService.getSustainabilityPracticesById(sustainabilityPracticesId);
            return ResponseEntity.ok(new ApiResponse("Found ", sustainabilityPractices));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{sustainabilityPracticesId}")
    public ResponseEntity<ApiResponse> updateSustainabilityPractices(@PathVariable Long sustainabilityPracticesId,@Valid @RequestBody SustainabilityPractices sustainabilityPractices) {

        try {
            SustainabilityPractices updatedSustainabilityPractices = sustainabilityPracticeService.updateSustainabilityPractices(sustainabilityPracticesId, sustainabilityPractices);
            return ResponseEntity.ok(new ApiResponse("SustainabilityPractices update success", updatedSustainabilityPractices));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{sustainabilityPracticesId}")
    public ResponseEntity<ApiResponse> deleteSustainabilityPractices(@PathVariable Long sustainabilityPracticesId) {
        try {
            sustainabilityPracticeService.deleteSustainabilityPractices(sustainabilityPracticesId);
            return ResponseEntity.ok(new ApiResponse("SustainabilityPractices deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
