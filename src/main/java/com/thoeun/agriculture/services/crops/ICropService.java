package com.thoeun.agriculture.services.crops;

import com.thoeun.agriculture.models.Crop;

import java.util.List;

public interface ICropService {
    Crop createSoil(Crop crop);
    Crop getCropById(Long cropId);
    List<Crop> getAllCrops();
    Crop updateCrop(Long cropId, Crop crop);
    void deleteCrop(Long cropId);
}

