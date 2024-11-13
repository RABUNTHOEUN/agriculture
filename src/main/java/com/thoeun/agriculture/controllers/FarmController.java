package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/farms")
@AllArgsConstructor
public class FarmController {


    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createFarm(@Valid @RequestBody Farm farm) {
        try {
            Farm createdFarm = farmService.createFarm(farm);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Farm created successfully", createdFarm));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllFarms() {
        try {
            List<Farm> farm = farmService.getAllFarms();
            return ResponseEntity.ok(new ApiResponse("Success", farm));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<ApiResponse> getFarmById(@PathVariable Long farmId) {
        try {
            Farm farm = farmService.getFarmById(farmId);
            return ResponseEntity.ok(new ApiResponse("Found ", farm));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{farmId}")
    public ResponseEntity<ApiResponse> updateFarm(@PathVariable Long farmId, @RequestBody Farm farmDetails) {
        try {
            Farm updatedFarm = farmService.updateFarm(farmId, farmDetails);
            return ResponseEntity.ok(new ApiResponse("Farm update success", updatedFarm));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{farmId}")
    public ResponseEntity<ApiResponse> deleteFarm(@PathVariable Long farmId) {
        try {
            farmService.deleteFarm(farmId);
            return ResponseEntity.ok(new ApiResponse("Farm deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
