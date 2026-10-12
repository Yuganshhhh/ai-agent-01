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

/**
 * Service behind the streaming chat endpoint.
 * <p>
 * Sends the user's message to the AI model together with the earlier
 * conversation, and gives the model three tools it may call:
 * calculator, weather and currency exchange. The reply is streamed back
 * token by token.
 * <p>
 * Note: the conversation history is kept in memory in a single list, so it is
 * shared by all callers and is cleared when the application restarts.
 */
@Service
public class ChatService {

    /** Spring AI client used to talk to the model. */
    private final ChatClient chatClient;

    /** In-memory conversation history (user and assistant messages). */
    private final List<Message> messageList = new ArrayList<Message>();

    /** System prompt that sets the behaviour and tone of the assistant. */
    private static final String SYSTEM_PROMPT = """
            Explain every philosophical theory in a deep/ human way.
            """;

    private final CalculatorTool calculatorTool;
    private final WeatherTool weatherTool;
    private final CurrencyExchangeTool currencyExchangeTool;

    /**
     * Creates the service and registers the tools the model can use.
     *
     * @param builder              Spring AI builder used to create the {@link ChatClient}
     * @param calculatorTool       tool for arithmetic
     * @param weatherTool          tool for current weather
     * @param currencyExchangeTool tool for exchange rates
     */
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

    /**
     * Sends a message to the AI and streams the reply.
     * <p>
     * The user's message is added to the history first. While the reply
     * streams in, each chunk is collected, and when streaming finishes the
     * full reply is saved to the history as an assistant message, so the model
     * remembers it in the next request.
     *
     * @param message the user's message
     * @return a stream of text chunks from the model
     */
    public Flux<String> chat(MessageDto message) {

        // Collects the streamed chunks so the full reply can be saved at the end
        StringBuilder sb = new StringBuilder();

        messageList.add(new UserMessage(message.getMessage()));

        return chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .messages(messageList)
                .user(message.getMessage())
                .tools(calculatorTool, weatherTool, currencyExchangeTool)
                .stream()
                .content()
                .doOnNext(sb::append)
                .doOnComplete(() -> {
                    // Save the complete reply to the conversation history
                    messageList.add(new AssistantMessage(sb.toString()));
                });
    }
}


// Example of a tool-aware system prompt that was used while testing the tools.
// Prompt which i have used:

//You are a helpful AI assistant with access to external tools.
//
//Follow these rules:
//        1. For Arithmetic operations ALWAYS use Calculator tool, use this for even trivial operations.
//        2. For Weather details operations ALWAYS use Weather tool.
//        3. For Currency Exchange operations ALWAYS use CurrencyExchange tool.
//        4. You can use single tools multiple times if you want to get the exact answer.
//        5. You can use multiple tools in multiple times if you want to get the exact answer.
//
//Keep the reply as fun and technical as possible
