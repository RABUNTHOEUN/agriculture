package com.thoeun.agriculture.services.revenue;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Revenue;
import com.thoeun.agriculture.repository.RevenueRepository;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RevenueService implements IRevenueService{

    private final RevenueRepository revenueRepository;
    private final IFarmService farmService;

    @Override
    public Revenue createRevenue(Revenue revenue) {
        Farm farm = farmService.getFarmById(revenue.getFarm().getFarmId());
        if (farm == null) {
            throw  new ResourceNotFoundException("Farm Not Found!");
        }
        if (revenueRepository.existsByProductName(revenue.getProductName())) {
            throw new AlreadyExistsException("Revenue with the same product name already exists in this Revenue!");
        }
        return revenueRepository.save(revenue);
    }

    @Override
    public Revenue getRevenueById(Long revenueId) {
        return revenueRepository.findById(revenueId)
                .orElseThrow(() -> new ResourceNotFoundException("Revenue not found with ID: " + revenueId));
    }

    @Override
    public List<Revenue> getAllRevenues() {
        try {
            return revenueRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Revenue updateRevenue(Long revenueId, Revenue revenue) {
        Revenue updateRevenue = revenueRepository.findById(revenueId).orElseThrow(() -> new ResourceNotFoundException("Revenue not found!"));
        updateRevenue.setProductName(revenue.getProductName());
        updateRevenue.setRevenueAmount(revenue.getRevenueAmount());
        updateRevenue.setRevenueDate(revenue.getRevenueDate());
        return revenueRepository.save(updateRevenue);
    }

    @Override
    public void deleteRevenue(Long revenueId) {
        revenueRepository.findById(revenueId).ifPresentOrElse(revenueRepository::delete, () -> {
            throw new ResourceNotFoundException("Revenue Not Found!");
        });
    }
}
