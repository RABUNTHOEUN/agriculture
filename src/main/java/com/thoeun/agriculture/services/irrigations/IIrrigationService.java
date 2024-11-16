package com.thoeun.agriculture.services.irrigations;

import com.thoeun.agriculture.models.Irrigation;

import java.util.List;

public interface IIrrigationService {
    Irrigation createIrrigation(Irrigation irrigation);
    Irrigation getIrrigationById(Long irrigationId);
    List<Irrigation> getAllIrrigation();
    Irrigation updateIrrigation(Long irrigationId, Irrigation irrigation);
    void deleteIrrigation(Long irrigationId);

}
