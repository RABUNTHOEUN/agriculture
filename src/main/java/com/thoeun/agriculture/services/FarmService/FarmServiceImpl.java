package com.thoeun.agriculture.services.FarmService;


import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.repository.FarmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Farm createFarm(Farm farm) {
        validateFarm(farm);
        if (farmRepository.existsByName(farm.getName())) {
            throw new IllegalArgumentException("Farm with this name already exists");
        }
        return farmRepository.save(farm);
    }
    private void validateFarm(Farm farm) {
        if (farm == null) {
            throw new IllegalArgumentException("Farm cannot be null");
        }

        if (farm.getName() == null || farm.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Farm name cannot be null or empty");
        }

        if (farm.getLocation() == null || farm.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Farm location cannot be null or empty");
        }

        if (farm.getOwnerName() == null || farm.getOwnerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
    }

    @Override
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    public Farm getFarmById(Long farmId) {
        // Use findById to retrieve the farm, then check if it exists
        return farmRepository.findById(farmId)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found with ID: " + farmId));
    }

    @Override
    public Farm updateFarm(Long farmId, Farm farmDetails) {
        Farm farm = farmRepository.findById(farmId).orElseThrow(() -> new RuntimeException("Farm not found"));
        farm.setName(farmDetails.getName());
        farm.setLocation(farmDetails.getLocation());
        farm.setOwnerName(farmDetails.getOwnerName());
        return farmRepository.save(farm);
    }

    @Override
    public void deleteFarm(Long farmId) {
        if (!farmRepository.existsById(farmId)) {
            throw new ResourceNotFoundException("Farm not found with ID: " + farmId);
        }
        farmRepository.deleteById(farmId);
    }
}
