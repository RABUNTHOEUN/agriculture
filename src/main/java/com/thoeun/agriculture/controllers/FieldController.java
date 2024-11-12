package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Field;
import com.thoeun.agriculture.services.FarmService.FarmService;
import com.thoeun.agriculture.services.FieldService.FieldServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
@AllArgsConstructor
public class FieldController {

   private final FieldServiceImpl fieldService;
   private final FarmService farmService;

    @PostMapping
    public ResponseEntity<Field> createFarm(@RequestBody Field field) {
        Field createdField = fieldService.createField(field);

        return new ResponseEntity<>(createdField, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Field> getAllFarms() {
        return fieldService.getAllFields();
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<Field> getFieldById(@PathVariable Long fieldId) {
        Field field = fieldService.getFieldById(fieldId);
        return ResponseEntity.ok(field);
    }

    @PutMapping("/{farmId}")
    public ResponseEntity<Field> updateFarm(@PathVariable Long fieldId, @RequestBody Field field) {
        Field updateField = fieldService.updateField(fieldId, field);
        return new ResponseEntity<>(updateField, HttpStatus.OK);
    }

    @DeleteMapping("/{farmId}")
    public ResponseEntity<String> deleteField(@PathVariable Long fieldId) {
        fieldService.deleteField(fieldId);
        return ResponseEntity.ok("Field deleted successfully.");
    }
}
