package com.thoeun.agriculture.services.marketPrices;

import com.thoeun.agriculture.models.MarketPrices;

import java.util.List;

public interface IMarketPriceServices {
    MarketPrices createMarketPrice(MarketPrices marketPrices);
    MarketPrices getMarketPriceById(Long marketPriceId);
    List<MarketPrices> getAllMarketPrices();
    MarketPrices updateMarketPrice(Long marketPriceId, MarketPrices marketPrices);
    void deleteMarketPrice(Long marketPriceId);
}
