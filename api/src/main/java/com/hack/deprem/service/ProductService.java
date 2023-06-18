package com.hack.deprem.service;

import com.hack.deprem.dto.request.CreateProductRequest;
import com.hack.deprem.model.Product;
import com.hack.deprem.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //create
    protected Product createProduct(String name, String productName, int number, boolean isHuman, String phoneNumber){
        Product product = new Product(name, productName, number, isHuman, phoneNumber);
        productRepository.save(product);
        return product;
    }
}
