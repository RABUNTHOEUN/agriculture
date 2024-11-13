package com.thoeun.agriculture.services.FarmService;


import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.repository.FarmRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class FarmService implements IFarmService {

    private final FarmRepository farmRepository;

    @Override
    public Farm createFarm(Farm farm) {
        if (farmRepository.existsByName(farm.getName())) {
            throw new AlreadyExistsException("Farm with this name already exists");
        }
        return farmRepository.save(farm);
    }

    @Override
    public List<Farm> getAllFarms() {
        try {
            return farmRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Farm getFarmById(Long farmId) {
        // Use findById to retrieve the farm, then check if it exists
        return farmRepository.findById(farmId)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found with ID: " + farmId));
    }

    @Override
    public Farm updateFarm(Long farmId, Farm farmDetails) {
        Farm farm = farmRepository.findById(farmId).orElseThrow(() -> new ResourceNotFoundException("Farm not found!"));
        farm.setName(farmDetails.getName());
        farm.setLocation(farmDetails.getLocation());
        farm.setOwnerName(farmDetails.getOwnerName());
        return farmRepository.save(farm);
    }

    @Override
    public void deleteFarm(Long farmId) {
        farmRepository.findById(farmId).ifPresentOrElse(farmRepository::delete, () -> {
            throw new ResourceNotFoundException("Farm Not Found!");
        });
    }
}
