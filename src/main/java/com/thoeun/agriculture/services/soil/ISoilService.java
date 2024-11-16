package com.thoeun.agriculture.services.soil;

import com.thoeun.agriculture.models.Soil;

import java.util.List;

public interface ISoilService {
    Soil createSoil(Soil soil);
    Soil getSoilById(Long soilId);
    List<Soil> getAllSoils();
    Soil updateSoil(Long soilId, Soil soil);
    void deleteSoil(Long soilId);
}

