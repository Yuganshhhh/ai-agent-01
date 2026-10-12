package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the AI Agent Tools demo application.
 * <p>
 * A Spring Boot and Spring AI project that shows how to give an AI model tools
 * (calculator, weather, currency exchange, file system) and how to build an
 * autonomous agent that generates websites.
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * Starts the Spring Boot application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
