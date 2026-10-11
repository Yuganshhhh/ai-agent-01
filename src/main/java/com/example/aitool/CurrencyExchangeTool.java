package com.example.aitool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * AI tool that fetches live currency exchange rates.
 * <p>
 * Calls the free Frankfurter API ({@code https://api.frankfurter.dev/v1}),
 * so the model gets up-to-date rates instead of guessing from its training data.
 */
@Component
public class CurrencyExchangeTool {

    /** HTTP client pre-configured with the Frankfurter API base URL. */
    private final RestClient restClient;

    /**
     * Creates the tool and builds the HTTP client.
     *
     * @param builder Spring-provided builder used to create the {@link RestClient}
     */
    public CurrencyExchangeTool(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://api.frankfurter.dev/v1").build();
    }

    /**
     * Gets the latest exchange rate between two currencies.
     *
     * @param from the base currency code, for example {@code USD}
     * @param to   the target currency code, for example {@code INR}
     * @return the raw JSON response from the Frankfurter API
     */
    @Tool(description = """
            This is a currency exchange tool.
            It is used for converting one currency to another.
            """)
    public String getExchangeRate(

            @ToolParam(description = """
                    It is a from parameter.
                    You can enter the currency which you want to exchange
                    """)
            String from,

            @ToolParam(description = """
                    It is a 'to' parameter.
                    You can enter the currency to which you want to exchange the 'from' parameter to.
                    """)
            String to) {

        // Log so we can see in the console when the model decides to use this tool
        System.out.println("Currency Exchange Tool called...");

        // GET /latest?base={from}&symbols={to}
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("base", from)
                        .queryParam("symbols", to)
                        .build())
                .retrieve()
                .body(String.class);
    }
}
