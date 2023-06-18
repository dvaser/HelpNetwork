package com.hack.deprem.controller;

import com.hack.deprem.dto.AssistanceDto;
import com.hack.deprem.dto.request.CreateAssistanceRequest;
import com.hack.deprem.service.AssistanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/assistance")
@CrossOrigin("*")
public class AssistanceController {
    private final AssistanceService assistanceService;

    public AssistanceController(AssistanceService assistanceService) {
        this.assistanceService = assistanceService;
    }

    @GetMapping("/assistances")
    public ResponseEntity<List<AssistanceDto>> getAssistances() {
        List<AssistanceDto> assistance = assistanceService.getAssistances();
        return new ResponseEntity<>(assistance, HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<List<AssistanceDto>> getAssistancesByLocation(@RequestParam("city") int city) {
        List<AssistanceDto> assistance = assistanceService.getAssistances();
        return new ResponseEntity<>(assistance, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAssistance(@RequestBody CreateAssistanceRequest createAssistanceRequest) {
        assistanceService.createAssistance(createAssistanceRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}