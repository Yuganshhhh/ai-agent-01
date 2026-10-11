package com.example.aitool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * AI tool that fetches the current weather for any location.
 * <p>
 * Calls the WeatherAPI service ({@code https://api.weatherapi.com/v1}).
 * The API key is read from the {@code weather.api-key} property, which is
 * filled from the {@code WEATHER_API_KEY} environment variable.
 */
@Component
public class WeatherTool {

    /** API key used to authenticate with WeatherAPI. */
    private final String apiKey;

    /** HTTP client pre-configured with the WeatherAPI base URL. */
    private final RestClient restClient;

    /**
     * Creates the tool and builds the HTTP client.
     *
     * @param apiKey  the WeatherAPI key from application configuration
     * @param builder Spring-provided builder used to create the {@link RestClient}
     */
    public WeatherTool(@Value("${weather.api-key}") String apiKey,
                       RestClient.Builder builder
                       ) {
        this.apiKey = apiKey;
        this.restClient = builder.baseUrl("https://api.weatherapi.com/v1").build();
    }

    /**
     * Gets the current weather for the given location.
     *
     * @param location a city name or place, for example {@code Delhi}
     * @return the raw JSON response from WeatherAPI
     */
    @Tool(description = "This is a Weather Tool used for obtaining the current weather")
    public String getWeatherDetails(
            @ToolParam(description = """
                            It is a location parameter.
                            You can enter any location for which you want to calculate the current weather.
                            """)
            String location) {

        // Log so we can see in the console when the model decides to use this tool
        System.out.println("Weather Tool called...");

        // GET /current.json?key={apiKey}&q={location}
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
