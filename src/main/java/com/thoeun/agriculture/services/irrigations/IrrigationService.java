package com.thoeun.agriculture.services.irrigations;

import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Field;
import com.thoeun.agriculture.models.Irrigation;
import com.thoeun.agriculture.repository.IrrigationRepository;
import com.thoeun.agriculture.services.FieldService.IFieldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IrrigationService implements IIrrigationService{

    private final IrrigationRepository irrigationRepository;
    private final IFieldService fieldService;

    @Override
    public Irrigation createIrrigation(Irrigation irrigation) {
        Field field = fieldService.getFieldById(irrigation.getField().getFieldId());
        if (field == null) {
            throw  new ResourceNotFoundException("Field Not Found!");
        }
        return irrigationRepository.save(irrigation);
    }

    @Override
    public Irrigation getIrrigationById(Long irrigationId) {
        return irrigationRepository.findById(irrigationId)
                .orElseThrow(() -> new ResourceNotFoundException("Irrigation not found with ID: " + irrigationId));
    }

    @Override
    public List<Irrigation> getAllIrrigation() {
        try {
            return irrigationRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Irrigation updateIrrigation(Long irrigationId, Irrigation irrigation) {
        Irrigation updateIrrigation = irrigationRepository.findById(irrigationId).orElseThrow(() -> new ResourceNotFoundException("Irrigation not found!"));
        updateIrrigation.setIrrigationDate(irrigation.getIrrigationDate());
        updateIrrigation.setWaterUsed(irrigation.getWaterUsed());
        updateIrrigation.setMoistureLevelBefore(irrigation.getMoistureLevelBefore());
        updateIrrigation.setMoistureLevelAfter(irrigation.getMoistureLevelAfter());
        return irrigationRepository.save(updateIrrigation);
    }

    @Override
    public void deleteIrrigation(Long irrigationId) {
        irrigationRepository.findById(irrigationId).ifPresentOrElse(irrigationRepository::delete, () -> {
            throw new ResourceNotFoundException("Irrigation Not Found!");
        });
    }
}
