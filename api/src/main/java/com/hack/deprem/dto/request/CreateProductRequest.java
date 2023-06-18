package com.hack.deprem.dto.request;

public record CreateProductRequest(
        String productName,
        int number,
        boolean isHuman,
        String phoneNumber
) {
}
