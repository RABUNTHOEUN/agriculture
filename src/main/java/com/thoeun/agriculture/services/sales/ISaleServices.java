package com.thoeun.agriculture.services.sales;

import com.thoeun.agriculture.models.Sales;

import java.util.List;

public interface ISaleServices {
    Sales createSales(Sales sales);
    Sales getSaleById(Long saleId);
    List<Sales> getAllSales();
    Sales updateSale(Long saleId, Sales sales);
    void deleteSale(Long saleId);
}
