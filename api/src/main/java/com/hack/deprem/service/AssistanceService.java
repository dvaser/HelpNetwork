package com.hack.deprem.service;

import com.hack.deprem.dto.AssistanceDto;
import com.hack.deprem.dto.request.CreateAssistanceRequest;
import com.hack.deprem.model.Assistance;
import com.hack.deprem.model.Product;
import com.hack.deprem.repository.AssistanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistanceService {
    private final AssistanceRepository assistanceRepository;
    private final ProductService productService;
    private final AssistancePointService assistancePointService;

    public AssistanceService(AssistanceRepository assistanceRepository,
                             ProductService productService,
                             AssistancePointService assistancePointService) {
        this.assistanceRepository = assistanceRepository;
        this.productService = productService;
        this.assistancePointService = assistancePointService;
    }

    public void createAssistance(CreateAssistanceRequest assistanceRequest){
        Product product = productService.createProduct(
                assistanceRequest.name(),
                assistanceRequest.productName(),
                assistanceRequest.number(),
                assistanceRequest.isHuman(),
                assistanceRequest.phoneNumber());
        Assistance assistance = new Assistance(product, assistanceRequest.from());
        assistanceRepository.save(assistance);
        assistancePointService.updateOnRoad(String.format("%d",assistanceRequest.from()), product);
    }

    public List<AssistanceDto> getAssistancesByCity(int city){
        return assistanceRepository.getAssistancesByFromCity(city).stream().map(AssistanceDto::convert).toList();
    }

    public List<AssistanceDto> getAssistances(){
        return assistanceRepository.findAll().stream().map(AssistanceDto::convert).toList();
    }

}
