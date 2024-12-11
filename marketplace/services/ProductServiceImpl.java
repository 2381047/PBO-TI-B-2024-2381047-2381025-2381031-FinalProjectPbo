package Marketplace.services;

import Marketplace.entities.Product;
import Marketplace.repositories.ProductRepository;

import java.util.ArrayList;

public class ProductServicesImpl implements ProductServices {
    private ProductRepository productRepository;

    public ProductServicesImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.update(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.delete(id);
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductDetails(int id) {
        return productRepository.findById(id);
    }
}