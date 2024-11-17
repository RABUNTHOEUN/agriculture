package com.thoeun.agriculture.services.healthRecords;

import com.thoeun.agriculture.models.HealthRecords;

import java.util.List;

public interface IHealthRecordService {
    HealthRecords createHealthRecords(HealthRecords healthRecords);
    HealthRecords getHealthRecordsById(Long healthRecordsId);
    List<HealthRecords> getAllHealthRecords();
    HealthRecords updateHealthRecords(Long healthRecordsId, HealthRecords healthRecords);
    void deleteHealthRecords(Long healthRecordsId);
}
