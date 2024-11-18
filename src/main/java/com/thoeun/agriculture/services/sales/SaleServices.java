package com.thoeun.agriculture.services.sales;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Livestock;
import com.thoeun.agriculture.models.Sales;
import com.thoeun.agriculture.repository.LivestockRepository;
import com.thoeun.agriculture.repository.SalesRepository;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleServices implements ISaleServices{

    private final SalesRepository salesRepository;
    private final IFarmService farmService;

    @Override
    public Sales createSales(Sales sales) {
        Farm farm = farmService.getFarmById(sales.getFarm().getFarmId());
        if (farm == null) {
            throw  new ResourceNotFoundException("Farm Not Found!");
        }
        if (salesRepository.existsByProductName(sales.getProductName())) {
            throw new AlreadyExistsException("Sale with the same product name already exists in this Sale!");
        }
        return salesRepository.save(sales);
    }

    @Override
    public Sales getSaleById(Long saleId) {
        return salesRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with ID: " + saleId));
    }

    @Override
    public List<Sales> getAllSales() {
        try {
            return salesRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Sales updateSale(Long saleId, Sales sales) {
        Sales updateSales = salesRepository.findById(saleId).orElseThrow(() -> new ResourceNotFoundException("Sale not found!"));
        updateSales.setProductName(sales.getProductName());
        updateSales.setQuantitySold(sales.getQuantitySold());
        updateSales.setSaleDate(sales.getSaleDate());
        updateSales.setRevenue(sales.getRevenue());
        updateSales.setBuyerName(sales.getBuyerName());
        return salesRepository.save(updateSales);
    }

    @Override
    public void deleteSale(Long saleId) {
        salesRepository.findById(saleId).ifPresentOrElse(salesRepository::delete, () -> {
            throw new ResourceNotFoundException("Sale Not Found!");
        });
    }
}
