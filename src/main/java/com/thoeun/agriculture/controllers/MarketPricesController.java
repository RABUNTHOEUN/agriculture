package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.MarketPrices;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.marketPrices.IMarketPriceServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequestMapping("${api.prefix}/marketPrices")
@AllArgsConstructor
public class MarketPricesController {

    private final IMarketPriceServices marketPriceServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createMarketPrices(@Valid @RequestBody MarketPrices marketPrices) {
        try {
            MarketPrices createdMarketPrices = marketPriceServices.createMarketPrice(marketPrices);
            return ResponseEntity.status(CREATED).body(new ApiResponse("MarketPrice created successfully", createdMarketPrices));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMarketPrices () {
        try {
            List<MarketPrices> marketPrices = marketPriceServices.getAllMarketPrices();
            return ResponseEntity.ok(new ApiResponse("Success", marketPrices));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{marketPriceId}")
    public ResponseEntity<ApiResponse> getMarketPricesById(@PathVariable Long marketPriceId) {
        try {
            MarketPrices marketPrices = marketPriceServices.getMarketPriceById(marketPriceId);
            return ResponseEntity.ok(new ApiResponse("Found ", marketPrices));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{marketPriceId}")
    public ResponseEntity<ApiResponse> updateMarketPrices(@PathVariable Long marketPriceId,@Valid @RequestBody MarketPrices marketPrices) {

        try {
            MarketPrices updatedMarketPrices = marketPriceServices.updateMarketPrice(marketPriceId, marketPrices);
            return ResponseEntity.ok(new ApiResponse("MarketPrices update success", updatedMarketPrices));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{marketPriceId}")
    public ResponseEntity<ApiResponse> deleteMarketPrices(@PathVariable Long marketPriceId) {
        try {
            marketPriceServices.deleteMarketPrice(marketPriceId);
            return ResponseEntity.ok(new ApiResponse("MarketPrice deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
