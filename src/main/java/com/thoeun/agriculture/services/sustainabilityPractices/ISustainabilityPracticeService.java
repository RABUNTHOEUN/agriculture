package com.thoeun.agriculture.services.sustainabilityPractices;

import com.thoeun.agriculture.models.SustainabilityPractices;

import java.util.List;

public interface ISustainabilityPracticeService {
    SustainabilityPractices createSustainabilityPractices(SustainabilityPractices sustainabilityPractices);
    SustainabilityPractices getSustainabilityPracticesById(Long sustainabilityPracticesId);
    List<SustainabilityPractices> getAllSustainabilityPractices();
    SustainabilityPractices updateSustainabilityPractices(Long sustainabilityPracticesId, SustainabilityPractices sustainabilityPractices);
    void deleteSustainabilityPractices(Long sustainabilityPracticesId);
}
