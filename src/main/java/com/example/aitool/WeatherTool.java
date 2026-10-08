package com.example.aitool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherTool {

    private final String apiKey;
    private final RestClient restClient;

    public WeatherTool(@Value("${weather.api-key}") String apiKey,
                       RestClient.Builder builder
                       ) {
        this.apiKey = apiKey;
        this.restClient = builder.baseUrl("https://api.weatherapi.com/v1").build();
    }

    @Tool(description = "This is a Weather Tool used for obtaining the current weather")
    public String getWeatherDetails(
            @ToolParam(description = """
                            It is a location parameter.
                            You can enter any location for which you want to calculate the current weather.
                            """)
            String location) {

        System.out.println("Weather Tool called...");

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", location)
                        .build())
                .retrieve()
                .body(String.class);
    }

}
