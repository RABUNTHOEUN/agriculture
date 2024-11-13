package com.thoeun.agriculture.services.FieldService;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Field;
import com.thoeun.agriculture.repository.FieldRepository;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FieldService implements IFieldService {


    private final FieldRepository fieldRepository;
    private final IFarmService farmService;

    @Override
    public Field createField(Long farmId, Field field) {
        Farm farm = farmService.getFarmById(farmId);
        if (farm == null) {
            throw new ResourceNotFoundException("Farm not Fount!");
        }

        if (fieldRepository.existsByFarmAndFieldName(farm, field.getFieldName())) {
            throw new AlreadyExistsException("Field with the same name already exists in this farm!");
        }

        field.setFarm(farm);
        return fieldRepository.save(field);
    }

    @Override
    public Field getFieldById(Long fieldId) {
        return fieldRepository.findById(fieldId)
                .orElseThrow(() -> new ResourceNotFoundException("Field Not Found" + fieldId));
    }

    @Override
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    @Override
    public Field updateField(Long Id, Field field) {
        return Optional.ofNullable(getFieldById(Id)).map(oldField -> {
            oldField.setFieldName(field.getFieldName());
            oldField.setSize(field.getSize());
            oldField.setCropType(field.getCropType());

            return fieldRepository.save(oldField);
        }).orElseThrow(() -> new ResourceNotFoundException("Field not Found"));
    }

    @Override
    public void deleteField(Long fieldId) {
        fieldRepository.findById(fieldId).ifPresentOrElse(fieldRepository::delete, () -> {
            throw new ResourceNotFoundException("Field Not Found!");
        });
    }
}
