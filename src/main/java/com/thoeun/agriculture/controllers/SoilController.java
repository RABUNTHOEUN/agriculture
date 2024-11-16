package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Soil;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FieldService.IFieldService;
import com.thoeun.agriculture.services.soil.ISoilService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/soils")
@AllArgsConstructor
public class SoilController {
    private final ISoilService soilService;
    private final IFieldService fieldService;

    @PostMapping
    public ResponseEntity<ApiResponse> createSoil(@Valid @RequestBody Soil soil) {
        try {
            try {
                fieldService.getFieldById(soil.getField().getFieldId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Soil createdSoil = soilService.createSoil(soil);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Soil created successfully", createdSoil));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllSoils () {
        try {
            List<Soil> soils = soilService.getAllSoils();
            return ResponseEntity.ok(new ApiResponse("Success", soils));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }




    @GetMapping("/{soilId}")
    public ResponseEntity<ApiResponse> getSoilById(@PathVariable Long soilId) {
        try {
            Soil soil = soilService.getSoilById(soilId);
            return ResponseEntity.ok(new ApiResponse("Found ", soil));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{soilId}")
    public ResponseEntity<ApiResponse> updateSoil(@PathVariable Long soilId,@Valid @RequestBody Soil soil) {

        try {
            Soil updatedSoil = soilService.updateSoil(soilId, soil);
            return ResponseEntity.ok(new ApiResponse("Soil update success", updatedSoil));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{soilId}")
    public ResponseEntity<ApiResponse> deleteSoil(@PathVariable Long soilId) {
        try {
            soilService.deleteSoil(soilId);
            return ResponseEntity.ok(new ApiResponse("Soil deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
