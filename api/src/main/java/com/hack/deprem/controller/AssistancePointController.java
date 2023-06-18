package com.hack.deprem.controller;

import com.hack.deprem.dto.AssistancePointDto;
import com.hack.deprem.dto.request.CreateAssistancePointRequest;
import com.hack.deprem.service.AssistancePointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/point")
@CrossOrigin("*")
public class AssistancePointController {
    private final AssistancePointService assistancePointService;

    public AssistancePointController(AssistancePointService assistancepointService) {
        this.assistancePointService = assistancepointService;
    }

    @GetMapping("/points")
    public ResponseEntity<List<AssistancePointDto>> getHelpsByLocation() {
        List<AssistancePointDto> points = assistancePointService.getPoints();
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @GetMapping("/helps/location")
    public ResponseEntity<AssistancePointDto> getHelpsByLocation(@RequestParam("location") String location) {
        AssistancePointDto point = assistancePointService.getPoint(location);
        return new ResponseEntity<>(point, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAssistancePoint(@RequestBody CreateAssistancePointRequest createAssistancePointRequest) {
        assistancePointService.createAssistancePoint(createAssistancePointRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}