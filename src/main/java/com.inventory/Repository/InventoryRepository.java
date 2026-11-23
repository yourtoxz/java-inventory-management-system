package com.inventory.Repository;

import com.inventory.Model.Product;
import java.util.List;
public interface InventoryRepository {

    void addProduct(Product product);
    Product findByCode(int code);
    boolean existsByCode(int code);
    boolean removeProduct(int code);
    boolean updateQuantity(int code, int quantity);

    List<Product> getProducts();
}
