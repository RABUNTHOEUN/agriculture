package com.thoeun.agriculture.services.sustainabilityPractices;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.SustainabilityPractices;
import com.thoeun.agriculture.repository.SustainabilityPracticesRepository;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SustainabilityPracticeService implements ISustainabilityPracticeService {

    private final SustainabilityPracticesRepository sustainabilityPracticesRepository;
    private final IFarmService farmService;

    @Override
    public SustainabilityPractices createSustainabilityPractices(SustainabilityPractices sustainabilityPractices) {
        Farm farm = farmService.getFarmById(sustainabilityPractices.getFarm().getFarmId());
        if (farm == null) {
            throw new ResourceNotFoundException("Farm Not Found!");
        }
        if (sustainabilityPracticesRepository.existsByPracticeName(sustainabilityPractices.getPracticeName())) {
            throw new AlreadyExistsException("SustainabilityPractices with the same practice name already exists in this SustainabilityPractices!");
        }
        return sustainabilityPracticesRepository.save(sustainabilityPractices);
    }

    @Override
    public SustainabilityPractices getSustainabilityPracticesById(Long sustainabilityPracticesId) {
        return sustainabilityPracticesRepository.findById(sustainabilityPracticesId)
                .orElseThrow(() -> new ResourceNotFoundException("SustainabilityPractices not found with ID: " + sustainabilityPracticesId));
    }

    @Override
    public List<SustainabilityPractices> getAllSustainabilityPractices() {
        try {
            return sustainabilityPracticesRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public SustainabilityPractices updateSustainabilityPractices(Long sustainabilityPracticesId, SustainabilityPractices sustainabilityPractices) {
        SustainabilityPractices updateSustainabilityPractices = sustainabilityPracticesRepository.findById(sustainabilityPracticesId).orElseThrow(() -> new ResourceNotFoundException("SustainabilityPractices not found!"));
        updateSustainabilityPractices.setPracticeName(sustainabilityPractices.getPracticeName());
        updateSustainabilityPractices.setImplementationDate(sustainabilityPractices.getImplementationDate());
        updateSustainabilityPractices.setCertificationReceived(sustainabilityPractices.getCertificationReceived());
        return sustainabilityPracticesRepository.save(updateSustainabilityPractices);
    }

    @Override
    public void deleteSustainabilityPractices(Long sustainabilityPracticesId) {
        sustainabilityPracticesRepository.findById(sustainabilityPracticesId).ifPresentOrElse(sustainabilityPracticesRepository::delete, () -> {
            throw new ResourceNotFoundException("SustainabilityPractices Not Found!");
        });
    }
}
