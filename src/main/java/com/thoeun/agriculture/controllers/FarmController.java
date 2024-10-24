package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.services.FarmService.FarmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmServiceImpl farmService;

    @PostMapping
    public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
        Farm createdFarm = farmService.createFarm(farm);
        return new ResponseEntity<>(createdFarm, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
        Farm farm = farmService.getFarmById(farmId);
        return ResponseEntity.ok(farm);
    }

    @PutMapping("/{farmId}")
    public ResponseEntity<Farm> updateFarm(@PathVariable Long farmId, @RequestBody Farm farmDetails) {
        Farm updatedFarm = farmService.updateFarm(farmId, farmDetails);
        return new ResponseEntity<>(updatedFarm, HttpStatus.OK);
    }

    @DeleteMapping("/{farmId}")
    public ResponseEntity<String> deleteFarm(@PathVariable Long farmId) {
        farmService.deleteFarm(farmId);
        return ResponseEntity.ok("Farm deleted successfully.");
    }
}
