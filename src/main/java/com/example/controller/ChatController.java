package com.example.controller;

import com.example.service.ChatService;
import com.example.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * REST controller for the AI chat, exposed under {@code /api}.
 * <p>
 * Replies are streamed using Server-Sent Events (SSE), so clients can
 * show the answer as it is generated instead of waiting for the whole reply.
 */
@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;

    /**
     * Creates the controller.
     *
     * @param chatService the service that talks to the AI model
     */
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Sends a message to the AI and streams the reply.
     * <p>
     * Example: {@code POST /api/chat} with body {@code {"message": "What is 25 * 4?"}}
     *
     * @param message the request body containing the user's message
     * @return a stream of text chunks (Server-Sent Events)
     */
    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chat(@RequestBody MessageDto message) {
        return chatService.chat(message);
    }
}
