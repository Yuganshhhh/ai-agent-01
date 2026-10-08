package com.example.aitool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CurrencyExchangeTool {

    private final RestClient restClient;

    public CurrencyExchangeTool(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://api.frankfurter.dev/v1").build();
    }

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

        System.out.println("Currency Exchange Tool called...");

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
