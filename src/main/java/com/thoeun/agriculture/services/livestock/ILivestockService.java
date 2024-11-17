package com.thoeun.agriculture.services.livestock;

import com.thoeun.agriculture.models.Livestock;

import java.util.List;

public interface ILivestockService {
    Livestock createLivestock(Livestock livestock);
    Livestock getLivestockById(Long livestockId);
    List<Livestock> getAllLivestock();
    Livestock updateLivestock(Long livestockId, Livestock livestock);
    void deleteLivestock(Long livestockId);
}
