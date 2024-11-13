package com.thoeun.agriculture.services.FarmService;

// FarmService.java
import com.thoeun.agriculture.models.Farm;

import java.util.List;

public interface IFarmService {
    Farm createFarm(Farm farm);
    Farm getFarmById(Long farmId);
    List<Farm> getAllFarms();
    Farm updateFarm(Long farmId, Farm farm);
    void deleteFarm(Long farmId);
}

// Repeat for other entities like Field, Crop, etc.
