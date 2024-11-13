package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Crop;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FieldService.IFieldService;
import com.thoeun.agriculture.services.crops.ICropService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/crops")
@AllArgsConstructor
public class CropController {

    private final ICropService cropService;
    private final IFieldService fieldService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCrop(@Valid @RequestBody Crop crop) {
        try {
            try {
                fieldService.getFieldById(crop.getField().getFieldId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Crop createdCrop = cropService.createCrop(crop);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Crop created successfully", createdCrop));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCrops () {
        try {
            List<Crop> crops = cropService.getAllCrops();
            return ResponseEntity.ok(new ApiResponse("Success", crops));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{cropId}")
    public ResponseEntity<ApiResponse> getCropById(@PathVariable Long cropId) {
        try {
            Crop crop = cropService.getCropById(cropId);
            return ResponseEntity.ok(new ApiResponse("Found ", crop));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{cropId}")
    public ResponseEntity<ApiResponse> updateCrop(@PathVariable Long cropId, @RequestBody Crop crop) {

        try {
            Crop updatedCrop = cropService.updateCrop(cropId, crop);
            return ResponseEntity.ok(new ApiResponse("Crop update success", updatedCrop));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{cropId}")
    public ResponseEntity<ApiResponse> deleteCrop(@PathVariable Long cropId) {
        try {
            cropService.deleteCrop(cropId);
            return ResponseEntity.ok(new ApiResponse("Crop deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
