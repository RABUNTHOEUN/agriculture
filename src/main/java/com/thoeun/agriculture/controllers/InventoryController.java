package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Inventory;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import com.thoeun.agriculture.services.inventory.IInventoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/inventories")
@AllArgsConstructor
public class InventoryController {

    private final IInventoryService inventoryService;
    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createInventory(@Valid @RequestBody Inventory inventory) {
        try {
            try {
                farmService.getFarmById(inventory.getFarm().getFarmId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Inventory createdInventory = inventoryService.createInventory(inventory);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Inventory created successfully", createdInventory));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllInventories () {
        try {
            List<Inventory> inventories = inventoryService.getAllInventories();
            return ResponseEntity.ok(new ApiResponse("Success", inventories));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{inventoryId}")
    public ResponseEntity<ApiResponse> getInventoryById(@PathVariable Long inventoryId) {
        try {
            Inventory inventory = inventoryService.getInventoryById(inventoryId);
            return ResponseEntity.ok(new ApiResponse("Found ", inventory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<ApiResponse> updateInventory(@PathVariable Long inventoryId,@Valid @RequestBody Inventory inventory) {

        try {
            Inventory updatedInventory = inventoryService.updateInventory(inventoryId, inventory);
            return ResponseEntity.ok(new ApiResponse("Inventory update success", updatedInventory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<ApiResponse> deleteInventory(@PathVariable Long inventoryId) {
        try {
            inventoryService.deleteInventory(inventoryId);
            return ResponseEntity.ok(new ApiResponse("Inventory deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
