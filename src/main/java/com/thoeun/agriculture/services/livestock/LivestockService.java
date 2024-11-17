package com.thoeun.agriculture.services.livestock;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Livestock;
import com.thoeun.agriculture.repository.LivestockRepository;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LivestockService implements ILivestockService {

    private final LivestockRepository livestockRepository;
    private final IFarmService farmService;

    @Override
    public Livestock createLivestock(Livestock livestock) {
        Farm farm = farmService.getFarmById(livestock.getFarm().getFarmId());
        if (farm == null) {
            throw  new ResourceNotFoundException("Farm Not Found!");
        }
        if (livestockRepository.existsByanimalType(livestock.getAnimalType())) {
            throw new AlreadyExistsException("Livestock with the same name already exists in this livestock!");
        }
        return livestockRepository.save(livestock);
    }

    @Override
    public Livestock getLivestockById(Long livestockId) {
        return livestockRepository.findById(livestockId)
                .orElseThrow(() -> new ResourceNotFoundException("Livestock not found with ID: " + livestockId));
    }

    @Override
    public List<Livestock> getAllLivestock() {
        try {
            return livestockRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Livestock updateLivestock(Long livestockId, Livestock livestock) {
        Livestock updateLivestock = livestockRepository.findById(livestockId).orElseThrow(() -> new ResourceNotFoundException("Livestock not found!"));
        updateLivestock.setAnimalType(livestock.getAnimalType());
        updateLivestock.setBreed(livestock.getBreed());
        updateLivestock.setDateOfBirth(livestock.getDateOfBirth());
        updateLivestock.setHealthStatus(livestock.getHealthStatus());
        updateLivestock.setVaccinationDate(livestock.getVaccinationDate());
        return livestockRepository.save(updateLivestock);
    }

    @Override
    public void deleteLivestock(Long livestockId) {
        livestockRepository.findById(livestockId).ifPresentOrElse(livestockRepository::delete, () -> {
            throw new ResourceNotFoundException("Livestock Not Found!");
        });
    }
}
