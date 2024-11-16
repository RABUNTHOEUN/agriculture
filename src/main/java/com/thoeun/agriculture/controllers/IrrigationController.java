package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Irrigation;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FieldService.IFieldService;
import com.thoeun.agriculture.services.irrigations.IIrrigationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/irrigation")
@AllArgsConstructor
public class IrrigationController {

    private final IIrrigationService irrigationService;
    private final IFieldService fieldService;

    @PostMapping
    public ResponseEntity<ApiResponse> createIrrigation(@Valid @RequestBody Irrigation irrigation) {
        try {
            try {
                fieldService.getFieldById(irrigation.getField().getFieldId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Irrigation createdIrrigation = irrigationService.createIrrigation(irrigation);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Irrigation created successfully", createdIrrigation));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllIrrigation () {
        try {
            List<Irrigation> irrigation = irrigationService.getAllIrrigation();
            return ResponseEntity.ok(new ApiResponse("Success", irrigation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{irrigationId}")
    public ResponseEntity<ApiResponse> getIrrigationById(@PathVariable Long irrigationId) {
        try {
            Irrigation irrigation = irrigationService.getIrrigationById(irrigationId);
            return ResponseEntity.ok(new ApiResponse("Found ", irrigation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{irrigationId}")
    public ResponseEntity<ApiResponse> updateIrrigation(@PathVariable Long irrigationId, @RequestBody Irrigation irrigation) {

        try {
            Irrigation updatedIrrigation = irrigationService.updateIrrigation(irrigationId, irrigation);
            return ResponseEntity.ok(new ApiResponse("Irrigation update success", updatedIrrigation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{irrigationId}")
    public ResponseEntity<ApiResponse> deleteIrrigation(@PathVariable Long irrigationId) {
        try {
            irrigationService.deleteIrrigation(irrigationId);
            return ResponseEntity.ok(new ApiResponse("Irrigation deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
