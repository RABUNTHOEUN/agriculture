package com.thoeun.agriculture.services.FieldService;

import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Field;
import com.thoeun.agriculture.repository.FieldRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FieldServiceImpl implements FieldService{


    private final FieldRepository fieldRepository;

    @Override
    public Field createField(Field field) {
        validateField(field);
        return fieldRepository.save(field);
    }

    private void validateField(Field field) {
        if (field == null) {
            throw new IllegalArgumentException("Farm cannot be null");
        }

        if (field.getFieldName() == null || field.getFieldName().trim().isEmpty()) {
            throw new IllegalArgumentException("Farm name cannot be null or empty");
        }

//        if (field.getSize() == null || field.getSize().isEmpty()) {
//            throw new IllegalArgumentException("Farm location cannot be null or empty");
//        }

//        if (field.getOwnerName() == null || field.getOwnerName().trim().isEmpty()) {
//            throw new IllegalArgumentException("Owner name cannot be null or empty");
//        }
    }

    @Override
    public Field getFieldById(Long fieldId) {
        return fieldRepository.findById(fieldId).orElseThrow(()-> new ResourceNotFoundException("Field Not Found" + fieldId));
    }

    @Override
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    @Override
    public Field updateField(Long farmId, Field field) {
        return null;
    }

    @Override
    public void deleteField(Long fieldId) {

    }
}
