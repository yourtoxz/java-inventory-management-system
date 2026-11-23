package com.inventory.Service;
import com.inventory.Model.Product;
import com.inventory.Repository.InventoryRepository;

import java.util.List;

public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        if (inventoryRepository == null) {
            throw new NullPointerException("InventoryRepository cannot be null");
        }
        this.inventoryRepository = inventoryRepository;
    }

    public void registerProduct(Product product) {
        inventoryRepository.addProduct(product);
    }

    public Product findByCode(int code){
       return inventoryRepository.findByCode(code);
    }

    public boolean existsByCode(int code){
        return inventoryRepository.existsByCode(code);
    }

    public boolean removeProduct(int code){
        return inventoryRepository.removeProduct(code);
    }

    public boolean updateQuantity(int code, int quantity){
        return inventoryRepository.updateQuantity(code, quantity);
    }

    public List<Product> getAllProducts(){
        return inventoryRepository.getProducts();
    }
}