package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Revenue;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import com.thoeun.agriculture.services.revenue.IRevenueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/revenue")
@AllArgsConstructor
public class RevenueController {

    private final IRevenueService revenueService;
    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createRevenue(@Valid @RequestBody Revenue revenue) {
        try {
            try {
                farmService.getFarmById(revenue.getFarm().getFarmId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Revenue createdRevenue = revenueService.createRevenue(revenue);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Revenue created successfully", createdRevenue));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRevenue () {
        try {
            List<Revenue> revenues = revenueService.getAllRevenues();
            return ResponseEntity.ok(new ApiResponse("Success", revenues));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{revenueId}")
    public ResponseEntity<ApiResponse> getRevenueById(@PathVariable Long revenueId) {
        try {
            Revenue revenue = revenueService.getRevenueById(revenueId);
            return ResponseEntity.ok(new ApiResponse("Found ", revenue));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{revenueId}")
    public ResponseEntity<ApiResponse> updateRevenue(@PathVariable Long revenueId,@Valid @RequestBody Revenue revenue) {

        try {
            Revenue updatedRevenue = revenueService.updateRevenue(revenueId, revenue);
            return ResponseEntity.ok(new ApiResponse("Revenue update success", updatedRevenue));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{revenueId}")
    public ResponseEntity<ApiResponse> deleteRevenue(@PathVariable Long revenueId) {
        try {
            revenueService.deleteRevenue(revenueId);
            return ResponseEntity.ok(new ApiResponse("Revenue deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
