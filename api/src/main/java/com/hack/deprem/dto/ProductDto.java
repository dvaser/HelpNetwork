package com.hack.deprem.dto;

import com.hack.deprem.model.Product;

public record ProductDto(
        String name,
        String productName,
        int number
) {
    public static ProductDto convert(Product product){
        return new ProductDto(product.getName() ,product.getProductName(), product.getNumber());
    }
}
