package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Livestock;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import com.thoeun.agriculture.services.livestock.ILivestockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/livestock")
@AllArgsConstructor
public class LivestockController {

    private final ILivestockService livestockService;
    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createLivestock(@Valid @RequestBody Livestock livestock) {
        try {
            try {
                farmService.getFarmById(livestock.getFarm().getFarmId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Livestock createdLivestock = livestockService.createLivestock(livestock);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Livestock created successfully", createdLivestock));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllLivestock () {
        try {
            List<Livestock> livestock = livestockService.getAllLivestock();
            return ResponseEntity.ok(new ApiResponse("Success", livestock));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{livestockId}")
    public ResponseEntity<ApiResponse> getLivestockById(@PathVariable Long livestockId) {
        try {
            Livestock livestock = livestockService.getLivestockById(livestockId);
            return ResponseEntity.ok(new ApiResponse("Found ", livestock));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{livestockId}")
    public ResponseEntity<ApiResponse> updateLivestock(@PathVariable Long livestockId,@Valid @RequestBody Livestock livestock) {

        try {
            Livestock updatedLivestock = livestockService.updateLivestock(livestockId, livestock);
            return ResponseEntity.ok(new ApiResponse("Livestock update success", updatedLivestock));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{livestockId}")
    public ResponseEntity<ApiResponse> deleteLivestock(@PathVariable Long livestockId) {
        try {
            livestockService.deleteLivestock(livestockId);
            return ResponseEntity.ok(new ApiResponse("Livestock deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
