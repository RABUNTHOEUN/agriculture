package com.thoeun.agriculture.services.FieldService;

import com.thoeun.agriculture.models.Field;
import com.thoeun.agriculture.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FieldServiceImpl implements FieldService{

    @Autowired
    private FieldRepository fieldRepository;
    @Override
    public Field createField(Field field) {
        return null;
    }

    @Override
    public Field getFieldById(Long fieldId) {
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return null;
    }

    @Override
    public Field updateField(Long farmId, Field field) {
        return null;
    }

    @Override
    public void deleteField(Long fieldId) {

    }
}
