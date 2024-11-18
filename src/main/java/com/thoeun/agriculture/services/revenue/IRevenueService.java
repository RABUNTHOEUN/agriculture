package com.thoeun.agriculture.services.revenue;

import com.thoeun.agriculture.models.Revenue;

import java.util.List;

public interface IRevenueService {
    Revenue createRevenue(Revenue revenue);
    Revenue getRevenueById(Long revenueId);
    List<Revenue> getAllRevenues();
    Revenue updateRevenue(Long revenueId, Revenue revenue);
    void deleteRevenue(Long revenueId);
}
