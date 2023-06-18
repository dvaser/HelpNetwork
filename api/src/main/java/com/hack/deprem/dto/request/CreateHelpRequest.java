package com.hack.deprem.dto.request;


public record CreateHelpRequest(
        String location,
        String name,
        String productName,
        int number,
        boolean isHuman,
        String phoneNumber
) {
}
