package com.inventory.Repository;

import com.inventory.Exception.DuplicateProductCodeException;
import com.inventory.Model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListInventoryRepository implements InventoryRepository{

    private final ArrayList<Product> inventory = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        if (product == null) {
            throw new NullPointerException("Product cannot be null.");
        }

        if (existsByCode(product.getCode())) {
            throw new DuplicateProductCodeException(
                    "A product with this code already exists."
            );
        }

        inventory.add(product);
    }

    @Override
    public boolean existsByCode(int code) {
        for (Product product : inventory) {
            if (code == product.getCode()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Product findByCode(int code) {
        for (Product product : inventory) {
            if (product.getCode() == code) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean removeProduct(int code) {
        Product productToRemove = findByCode(code);

        if (productToRemove != null) {
            inventory.remove(productToRemove);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateQuantity(int code, int newQuantity) {
        Product product = findByCode(code);

        if (product == null) {
            return false;
        }

        product.setQuantity(newQuantity);
        return true;
    }

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(inventory);
    }
}
