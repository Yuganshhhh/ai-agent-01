package com.example.controller;

import com.example.dto.MessageDto;
import com.example.service.GenerateWebsiteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2")
public class GenerateWebsiteController {

    private final GenerateWebsiteService generateWebsiteService;

    public GenerateWebsiteController(GenerateWebsiteService generateWebsiteService) {
        this.generateWebsiteService = generateWebsiteService;
    }

    @PostMapping("/generate")
    public String generateWebsite(@RequestBody MessageDto messageDto) {
        return generateWebsiteService.generateWebsite(messageDto);
    }
}
