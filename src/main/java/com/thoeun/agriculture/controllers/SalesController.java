package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Sales;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import com.thoeun.agriculture.services.sales.ISaleServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/sales")
@AllArgsConstructor
public class SalesController {

    private final ISaleServices saleServices;
    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createSales(@Valid @RequestBody Sales sales) {
        try {
            try {
                farmService.getFarmById(sales.getFarm().getFarmId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Sales createdSales = saleServices.createSales(sales);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Sale created successfully", createdSales));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllSales () {
        try {
            List<Sales> sales = saleServices.getAllSales();
            return ResponseEntity.ok(new ApiResponse("Success", sales));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{saleId}")
    public ResponseEntity<ApiResponse> getSaleById(@PathVariable Long saleId) {
        try {
            Sales sales = saleServices.getSaleById(saleId);
            return ResponseEntity.ok(new ApiResponse("Found ", sales));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<ApiResponse> updateSale(@PathVariable Long saleId,@Valid @RequestBody Sales sales) {

        try {
            Sales updatedSale = saleServices.updateSale(saleId, sales);
            return ResponseEntity.ok(new ApiResponse("Sale update success", updatedSale));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<ApiResponse> deleteSale(@PathVariable Long saleId) {
        try {
            saleServices.deleteSale(saleId);
            return ResponseEntity.ok(new ApiResponse("Sale deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
