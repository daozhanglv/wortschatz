package io.github.daozhanglv.wortschatz.llm;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class DeepSeekClient {

    private final RestClient restClient;
    private final DeepSeekProperties properties;

    public DeepSeekClient(RestClient.Builder builder, DeepSeekProperties properties) {
        this.properties = properties;
        this.restClient = builder
                .baseUrl(properties.baseUrl())
                .build();
    }

    public String chat(String userPrompt) {
        if (userPrompt == null || userPrompt.isBlank()) {
            throw new IllegalArgumentException("userPrompt must not be blank");
        }
        String apiKey = properties.apiKey();


        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("DEEPSEEK_API_KEY is missing");
        }

        ChatCompletionRequest request = new ChatCompletionRequest(
                properties.model(),
                List.of(new ChatMessage("user", userPrompt))
        );

        ChatCompletionResponse response = restClient.post()
                .uri("chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(ChatCompletionResponse.class);

        if (response == null || response.choices() == null || response.choices().isEmpty()) {
            throw new IllegalStateException("DeepSeek response contained no choices");
        }

        ChatMessage message = response.choices().getFirst().message();

        if (message == null || message.content() == null) {
            throw new IllegalStateException("DeepSeek response message content is missing");
        }

        return message.content();
    }
}