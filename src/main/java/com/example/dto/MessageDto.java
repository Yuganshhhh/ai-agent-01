package com.example.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Request body used by the chat and website generation endpoints.
 * <p>
 * Example JSON: {@code {"message": "Hello"}}.
 * Lombok generates the getter and setter for the field.
 */
@Getter
@Setter
public class MessageDto {

    /** The text the user sends to the AI. */
    private String message;
}
