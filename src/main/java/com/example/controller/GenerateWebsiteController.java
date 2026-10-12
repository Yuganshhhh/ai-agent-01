package com.example.controller;

import com.example.dto.MessageDto;
import com.example.service.GenerateWebsiteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for the website generation agent, exposed under {@code /api2}.
 */
@RestController
@RequestMapping("/api2")
public class GenerateWebsiteController {

    private final GenerateWebsiteService generateWebsiteService;

    /**
     * Creates the controller.
     *
     * @param generateWebsiteService the service that runs the website agent
     */
    public GenerateWebsiteController(GenerateWebsiteService generateWebsiteService) {
        this.generateWebsiteService = generateWebsiteService;
    }

    /**
     * Asks the AI agent to build (or modify) a website.
     * <p>
     * Example: {@code POST /api2/generate} with body
     * {@code {"message": "Build a landing page for a coffee shop"}}.
     * The generated files are saved in the {@code generated-sites} folder.
     * This request blocks until the agent has finished all of its work.
     *
     * @param messageDto the request body containing the website description
     * @return the agent's short summary of what it created
     */
    @PostMapping("/generate")
    public String generateWebsite(@RequestBody MessageDto messageDto) {
        return generateWebsiteService.generateWebsite(messageDto);
    }
}
