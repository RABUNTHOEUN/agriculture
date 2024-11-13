package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Field;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import com.thoeun.agriculture.services.FieldService.IFieldService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/fields")
@AllArgsConstructor
public class FieldController {

    private final IFieldService fieldService;
    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createField(@Valid @RequestBody Field field, @RequestParam Long farmId) {
        try {
            try {
                farmService.getFarmById(farmId);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Field createdField = fieldService.createField(farmId, field);
            return ResponseEntity.ok(new ApiResponse("Field create success!", createdField));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllFarms() {
        try {
            List<Field> fields = fieldService.getAllFields();
            return ResponseEntity.ok(new ApiResponse("Found", fields));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<ApiResponse> getFieldById(@PathVariable Long fieldId) {
        try {
            Field field = fieldService.getFieldById(fieldId);
            return ResponseEntity.ok(new ApiResponse("Found", field));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Not found!", INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/{fieldId}")
    public ResponseEntity<Field> updateFarm(@PathVariable Long fieldId, @RequestBody Field field) {
        Field updateField = fieldService.updateField(fieldId, field);
        return new ResponseEntity<>(updateField, HttpStatus.OK);
    }

    @DeleteMapping("/{fieldId}")
    public ResponseEntity<String> deleteField(@PathVariable Long fieldId) {
        fieldService.deleteField(fieldId);
        return ResponseEntity.ok("Field deleted successfully.");
    }
}
