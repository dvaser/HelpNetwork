package com.hack.deprem.controller;

import com.hack.deprem.dto.HelpDto;
import com.hack.deprem.dto.request.CreateHelpRequest;
import com.hack.deprem.service.HelpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/help")
@CrossOrigin("*")
public class HelpController {
    private final HelpService helpService;

    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    @GetMapping("/helps")
    public ResponseEntity<List<HelpDto>> getHelpsByLocation() {
        List<HelpDto> helps = helpService.getHelps();
        return new ResponseEntity<>(helps, HttpStatus.OK);
    }

    @GetMapping("/helps/location")
    public ResponseEntity<List<HelpDto>> getHelpsByLocation(@RequestParam("location") String location) {
        List<HelpDto> helps = helpService.getHelpsByLocation(location);
        return new ResponseEntity<>(helps, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCustomer(@RequestBody CreateHelpRequest createHelpRequest) {
        System.out.println(createHelpRequest);
        helpService.createHelp(createHelpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/confirm")
    public ResponseEntity<Void> confirmStatus(@RequestParam("uuid") String uuid) {
        helpService.confirmStatus(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reject")
    public ResponseEntity<Void> rejectStatus(@RequestParam("uuid") String uuid) {
        helpService.rejectStatus(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}