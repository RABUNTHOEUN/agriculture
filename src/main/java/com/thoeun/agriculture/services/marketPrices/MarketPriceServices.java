package com.thoeun.agriculture.services.marketPrices;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.MarketPrices;
import com.thoeun.agriculture.repository.MarketPricesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarketPriceServices implements IMarketPriceServices{

    private final MarketPricesRepository marketPricesRepository;

    @Override
    public MarketPrices createMarketPrice(MarketPrices marketPrices) {

        if (marketPricesRepository.existsByProductName(marketPrices.getProductName())) {
            throw new AlreadyExistsException("MarketPrice with the same product name already exists in this MarketPrice!");
        }
        return marketPricesRepository.save(marketPrices);
    }

    @Override
    public MarketPrices getMarketPriceById(Long marketPriceId) {
        return marketPricesRepository.findById(marketPriceId)
                .orElseThrow(() -> new ResourceNotFoundException("MarketPrice not found with ID: " + marketPriceId));
    }

    @Override
    public List<MarketPrices> getAllMarketPrices() {
        try {
            return marketPricesRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public MarketPrices updateMarketPrice(Long marketPriceId, MarketPrices marketPrices) {
        MarketPrices updateMarketPrice = marketPricesRepository.findById(marketPriceId).orElseThrow(() -> new ResourceNotFoundException("MarketPrice not found!"));
        updateMarketPrice.setProductName(marketPrices.getProductName());
        updateMarketPrice.setMarketLocation(marketPrices.getMarketLocation());
        updateMarketPrice.setPrice(marketPrices.getPrice());
        updateMarketPrice.setDateRecorded(marketPrices.getDateRecorded());
        return marketPricesRepository.save(updateMarketPrice);
    }

    @Override
    public void deleteMarketPrice(Long marketPriceId) {
        marketPricesRepository.findById(marketPriceId).ifPresentOrElse(marketPricesRepository::delete, () -> {
            throw new ResourceNotFoundException("MarketPrice Not Found!");
        });
    }
}
