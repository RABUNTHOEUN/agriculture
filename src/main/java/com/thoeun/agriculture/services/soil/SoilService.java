package com.thoeun.agriculture.services.soil;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Field;
import com.thoeun.agriculture.models.Soil;
import com.thoeun.agriculture.repository.SoilRepository;
import com.thoeun.agriculture.services.FieldService.IFieldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SoilService implements ISoilService{

    private final SoilRepository soilRepository;
    private final IFieldService fieldService;

    @Override
    public Soil createSoil(Soil soil) {
        Field field = fieldService.getFieldById(soil.getField().getFieldId());
        if (field == null) {
            throw  new ResourceNotFoundException("Field Not Found!");
        }
//        if (soilRepository.existsById(soil.getSoilId())) {
//            throw new AlreadyExistsException("Soil with this Id already exists");
//        }
        return soilRepository.save(soil);
    }

    @Override
    public Soil getSoilById(Long soilId) {
        return soilRepository.findById(soilId)
                .orElseThrow(() -> new ResourceNotFoundException("Soil not found with ID: " + soilId));
    }

    @Override
    public List<Soil> getAllSoils() {
        try {
            return soilRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Soil updateSoil(Long soilId, Soil soil) {
        Soil updateSoil = soilRepository.findById(soilId).orElseThrow(() -> new ResourceNotFoundException("Soil not found!"));
        updateSoil.setPH(soil.getPH());
        updateSoil.setNitrogenLevel(soil.getNitrogenLevel());
        updateSoil.setPhosphorusLevel(soil.getPhosphorusLevel());
        updateSoil.setPotassiumLevel(soil.getPotassiumLevel());
        updateSoil.setMoistureLevel(soil.getMoistureLevel());
        updateSoil.setTestDate(soil.getTestDate());
        return soilRepository.save(updateSoil);
    }

    @Override
    public void deleteSoil(Long soilId) {
        soilRepository.findById(soilId).ifPresentOrElse(soilRepository::delete, () -> {
            throw new ResourceNotFoundException("Soil Not Found!");
        });
    }
}
