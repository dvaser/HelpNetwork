package com.hack.deprem.dto;

import com.hack.deprem.model.Help;
import com.hack.deprem.model.Product;

import java.util.List;

public record HelpDto(
        String uuid,
        String lat,
        String lon,
        Product product,
        short status
) {
    public static HelpDto convert(Help help){
        String lat;
        String lon;
        if(!help.getLocation().isEmpty()&& help.getProduct().getNumber() != 3){
            String[] parts = help.getLocation().split("-");

            lat = parts[0];
            lon = parts[1];
        }
        else if(help.getProduct().getNumber() == 3){
            lat = help.getLocation();
            lon = "-1";
        }
        else{
            lat = "-1";
            lon = "-1";
        }
        return new HelpDto(help.getUuid(), lat, lon, help.getProduct(), help.getStatus());
    }
}
