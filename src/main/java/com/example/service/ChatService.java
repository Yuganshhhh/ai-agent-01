package com.example.service;

import com.example.dto.MessageDto;
import com.example.aitool.CalculatorTool;
import com.example.aitool.CurrencyExchangeTool;
import com.example.aitool.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final List<Message> messageList = new ArrayList<Message>();
    private static final String SYSTEM_PROMPT = """
            Explain every philosophical theory in a deep/ human way.
            """;

    private final CalculatorTool calculatorTool;
    private final WeatherTool weatherTool;
    private final CurrencyExchangeTool currencyExchangeTool;

    @Autowired
    public ChatService(ChatClient.Builder builder,
                       CalculatorTool calculatorTool,
                       WeatherTool weatherTool,
                       CurrencyExchangeTool currencyExchangeTool) {
        this.chatClient = builder.build();
        this.calculatorTool = calculatorTool;
        this.weatherTool = weatherTool;
        this.currencyExchangeTool = currencyExchangeTool;
    }

    public Flux<String> chat(MessageDto message) {

        StringBuilder sb = new StringBuilder();

        messageList.add(new UserMessage(message.getMessage()));

        return chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .messages(messageList)
//                .user(message.getMessage())
//                .tools(calculatorTool, weatherTool, currencyExchangeTool)
                .stream()
                .content()
                .doOnNext(sb::append)
                .doOnComplete(() -> {
                    messageList.add(new AssistantMessage(sb.toString()));
                });
    }
}


//You are a helpful AI assistant with access to external tools.
//
//Follow these rules:
//        1. For Arithmetic operations ALWAYS use Calculator tool, use this for even trivial operations.
//            2. For Weather details operations ALWAYS use Weather tool.
//        3. For Currency Exchange operations ALWAYS use CurrencyExchange tool.
//        4. You can use single tools multiple times if you want to get the exact answer.
//            5. You can use multiple tools in multiple times if you want to get the exact answer.
//
//Do not reply in more than 1 line.
//Keep the reply short and fun as possible.
