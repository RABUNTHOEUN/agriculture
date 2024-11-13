package com.thoeun.agriculture.services.crops;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Crop;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Field;
import com.thoeun.agriculture.repository.CropRepository;
import com.thoeun.agriculture.services.FieldService.IFieldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CropService implements ICropService{

    private final CropRepository cropRepository;
    private final IFieldService fieldService;

    @Override
    public Crop createCrop(Crop crop) {
        Field field = fieldService.getFieldById(crop.getField().getFieldId());
        if (field == null) {
            throw  new ResourceNotFoundException("Field Not Found!");
        }
        if (cropRepository.existsByCropName(crop.getCropName())) {
            throw new AlreadyExistsException("Crop with this name already exists");
        }
        return cropRepository.save(crop);
    }

    @Override
    public Crop getCropById(Long cropId) {
        return cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with ID: " + cropId));
    }

    @Override
    public List<Crop> getAllCrops() {
        try {
            return cropRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Crop updateCrop(Long cropId, Crop crop) {

        Crop updateCrop = cropRepository.findById(cropId).orElseThrow(() -> new ResourceNotFoundException("Crop not found!"));
        if (cropRepository.existsByCropName(crop.getCropName())) {
            throw new AlreadyExistsException("Crop with this name already exists");
        }
        updateCrop.setCropName(crop.getCropName());
        updateCrop.setPlantingDate(crop.getPlantingDate());
        updateCrop.setHarvestDate(crop.getHarvestDate());
        updateCrop.setExpectedYield(crop.getExpectedYield());
        updateCrop.setActualYield(crop.getActualYield());
        return cropRepository.save(updateCrop);
    }

    @Override
    public void deleteCrop(Long cropId) {
        cropRepository.findById(cropId).ifPresentOrElse(cropRepository::delete, () -> {
            throw new ResourceNotFoundException("Crop Not Found!");
        });
    }
}
