package com.thoeun.agriculture.services.healthRecords;

import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.HealthRecords;
import com.thoeun.agriculture.models.Livestock;
import com.thoeun.agriculture.repository.HealthRecordsRepository;
import com.thoeun.agriculture.services.livestock.ILivestockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HealthRecordService implements IHealthRecordService{

    private final HealthRecordsRepository healthRecordsRepository;
    private final ILivestockService livestockService;

    @Override
    public HealthRecords createHealthRecords(HealthRecords healthRecords) {
        Livestock livestock = livestockService.getLivestockById(healthRecords.getLivestock().getLivestockId());
        if (livestock == null) {
            throw  new ResourceNotFoundException("Livestock Not Found!");
        }
        return healthRecordsRepository.save(healthRecords);
    }

    @Override
    public HealthRecords getHealthRecordsById(Long healthRecordsId) {
        return healthRecordsRepository.findById(healthRecordsId)
                .orElseThrow(() -> new ResourceNotFoundException("HealthRecord not found with ID: " + healthRecordsId));
    }

    @Override
    public List<HealthRecords> getAllHealthRecords() {
        try {
            return healthRecordsRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public HealthRecords updateHealthRecords(Long healthRecordsId, HealthRecords healthRecords) {
        HealthRecords updateHealthRecords = healthRecordsRepository.findById(healthRecordsId).orElseThrow(() -> new ResourceNotFoundException("HealthRecord not found!"));
        updateHealthRecords.setCheckupDate(healthRecords.getCheckupDate());
        updateHealthRecords.setDiagnosis(healthRecords.getDiagnosis());
        updateHealthRecords.setTreatment(healthRecords.getTreatment());
        updateHealthRecords.setNextCheckupDate(updateHealthRecords.getNextCheckupDate());
        return healthRecordsRepository.save(updateHealthRecords);
    }

    @Override
    public void deleteHealthRecords(Long healthRecordsId) {
        healthRecordsRepository.findById(healthRecordsId).ifPresentOrElse(healthRecordsRepository::delete, () -> {
            throw new ResourceNotFoundException("HealthRecord Not Found!");
        });
    }
}
