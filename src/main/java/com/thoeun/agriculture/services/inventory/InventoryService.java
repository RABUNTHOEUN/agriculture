package com.thoeun.agriculture.services.inventory;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.Inventory;
import com.thoeun.agriculture.repository.InventoryRepository;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService implements IInventoryService {

    private final InventoryRepository inventoryRepository;
    private final IFarmService farmService;

    @Override
    public Inventory createInventory(Inventory inventory) {
        Farm farm = farmService.getFarmById(inventory.getFarm().getFarmId());
        if (farm == null) {
            throw new ResourceNotFoundException("Farm Not Found!");
        }
        if (inventoryRepository.existsByProductName(inventory.getProductName())) {
            throw new AlreadyExistsException("Inventory with the same name already exists in this Inventory!");
        }
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory getInventoryById(Long inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with ID: " + inventoryId));
    }

    @Override
    public List<Inventory> getAllInventories() {
        try {
            return inventoryRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Inventory updateInventory(Long inventoryId, Inventory inventory) {
        Inventory updateInventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new ResourceNotFoundException("Inventory not found!"));
        updateInventory.setProductName(inventory.getProductName());
        updateInventory.setQuantity(inventory.getQuantity());
        updateInventory.setAvailableFrom(inventory.getAvailableFrom());
        updateInventory.setPricePerUnit(inventory.getPricePerUnit());
        return inventoryRepository.save(updateInventory);
    }

    @Override
    public void deleteInventory(Long inventoryId) {
        inventoryRepository.findById(inventoryId).ifPresentOrElse(inventoryRepository::delete, () -> {
            throw new ResourceNotFoundException("Inventory Not Found!");
        });
    }
}
