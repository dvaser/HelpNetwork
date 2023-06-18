package com.hack.deprem.dto;

import com.hack.deprem.model.Assistance;
import com.hack.deprem.model.Product;

public record AssistanceDto(
        Product product,
        int fromCity,
        String toLocation

) {
    public static AssistanceDto convert(Assistance assistance){
        return new AssistanceDto(assistance.getProduct(), assistance.getFromCity(), assistance.getToLocation());
    }
}
