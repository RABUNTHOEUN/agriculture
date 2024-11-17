package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.HealthRecords;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.healthRecords.IHealthRecordService;
import com.thoeun.agriculture.services.livestock.ILivestockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/healthRecords")
@AllArgsConstructor
public class HealthRecordController {

    private final ILivestockService livestockService;
    private final IHealthRecordService healthRecordService;

    @PostMapping
    public ResponseEntity<ApiResponse> createHealthRecords(@Valid @RequestBody HealthRecords healthRecords) {
        try {
            try {
                livestockService.getLivestockById(healthRecords.getLivestock().getLivestockId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            HealthRecords createdHealthRecords = healthRecordService.createHealthRecords(healthRecords);
            return ResponseEntity.status(CREATED).body(new ApiResponse("HealthRecords created successfully", createdHealthRecords));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllHealthRecords () {
        try {
            List<HealthRecords> healthRecords = healthRecordService.getAllHealthRecords();
            return ResponseEntity.ok(new ApiResponse("Success", healthRecords));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{healthRecordsId}")
    public ResponseEntity<ApiResponse> getHealthRecordById(@PathVariable Long healthRecordsId) {
        try {
            HealthRecords healthRecord = healthRecordService.getHealthRecordsById(healthRecordsId);
            return ResponseEntity.ok(new ApiResponse("Found ", healthRecord));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{healthRecordsId}")
    public ResponseEntity<ApiResponse> updateHealthRecord(@PathVariable Long healthRecordsId,@Valid @RequestBody HealthRecords healthRecords) {

        try {
            HealthRecords updatedHealthRecord = healthRecordService.updateHealthRecords(healthRecordsId, healthRecords);
            return ResponseEntity.ok(new ApiResponse("HealthRecord update success", updatedHealthRecord));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{healthRecordsId}")
    public ResponseEntity<ApiResponse> deleteHealthRecord(@PathVariable Long healthRecordsId) {
        try {
            healthRecordService.deleteHealthRecords(healthRecordsId);
            return ResponseEntity.ok(new ApiResponse("HealthRecord deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
