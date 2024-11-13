package com.thoeun.agriculture.services.FieldService;

// FarmService.java
import com.thoeun.agriculture.models.Field;

import java.util.List;

public interface IFieldService {
    Field createField(Field field);
    Field getFieldById(Long fieldId);
    List<Field> getAllFields();
    Field updateField(Long farmId, Field field);
    void deleteField(Long fieldId);
}

// Repeat for other entities like Field, Crop, etc.
