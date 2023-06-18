package com.hack.deprem.service;


import com.hack.deprem.dto.HelpDto;
import com.hack.deprem.dto.request.CreateHelpRequest;
import com.hack.deprem.model.Help;
import com.hack.deprem.model.Product;
import com.hack.deprem.repository.HelpRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpService {
    private final HelpRepository helpRepository;
    private final ProductService productService;
    private final AssistancePointService assistancePointService;

    public HelpService(
            HelpRepository helpRepository,
            ProductService productService,
            AssistancePointService assistancePointService) {
        this.helpRepository = helpRepository;
        this.productService = productService;
        this.assistancePointService = assistancePointService;
    }

    public List<HelpDto> getHelps(){
        return helpRepository.findAll().stream().map(HelpDto::convert).toList();
    }
    public List<HelpDto> getHelpsByLocation(String location){
        return helpRepository.getHelpsByLocation(location).stream().map(HelpDto::convert).toList();
    }

    public void createHelp(CreateHelpRequest helpRequest){
        Product product = productService.createProduct(
                helpRequest.name(),
                helpRequest.productName(),
                helpRequest.number(),
                helpRequest.isHuman(),
                helpRequest.phoneNumber());

        Help help = new Help(helpRequest.location(), product); //Todo
        helpRepository.save(help);
        assistancePointService.updateNeed(helpRequest.location(), product);
    }

    public void confirmStatus(String uuid){
        Help help = helpRepository.findById(uuid).get();
        help.setStatus((short) 1);
        helpRepository.save(help);
    }

    public void rejectStatus(String uuid){
        Help help = helpRepository.findById(uuid).get();
        help.setStatus((short) 2);
        helpRepository.save(help);
    }
}
