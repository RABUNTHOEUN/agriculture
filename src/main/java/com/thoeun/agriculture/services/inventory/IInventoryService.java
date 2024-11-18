package com.thoeun.agriculture.services.inventory;

import com.thoeun.agriculture.models.Inventory;

import java.util.List;

public interface IInventoryService {
    Inventory createInventory(Inventory inventory);
    Inventory getInventoryById(Long inventoryId);
    List<Inventory> getAllInventories();
    Inventory updateInventory(Long inventoryId, Inventory inventory);
    void deleteInventory(Long inventoryId);
}
