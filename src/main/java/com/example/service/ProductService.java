package com.example.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;

@Service
@SuppressWarnings("rawtypes")
public class ProductService extends MainService<Product>{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        super(); // Passing repository to the parent class
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    public ArrayList<Product> getProducts(){
        return productRepository.getProducts();
    }

    public Product getProductById(UUID productId){
        return productRepository.getProductById(productId);
    }

    public Product updateProduct(UUID productId, String newName, double newPrice){
        return productRepository.updateProduct(productId, newName, newPrice);
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds){
        productRepository.applyDiscount(discount, productIds);
    }

    public void deleteProductById(UUID productId){
        productRepository.deleteProductById(productId);
    }
    
}
