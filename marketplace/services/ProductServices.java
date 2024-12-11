package Marketplace.services;

import Marketplace.entities.Product;

import java.util.ArrayList;

public interface ProductServices {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
    ArrayList<Product> getAllProducts();
    Product getProductDetails(int id);
}