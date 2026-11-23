package com.inventory;

import com.inventory.Repository.ArrayListInventoryRepository;
import com.inventory.Repository.InventoryRepository;
import com.inventory.Service.InventoryService;

public class AppConfig {
    private final InventoryRepository inventoryRepository = new ArrayListInventoryRepository();
    private final InventoryService inventoryService;

    public AppConfig(){
        inventoryService = new InventoryService(inventoryRepository);
    }

    public InventoryService createInventoryService(){

        return inventoryService;
    }
}
