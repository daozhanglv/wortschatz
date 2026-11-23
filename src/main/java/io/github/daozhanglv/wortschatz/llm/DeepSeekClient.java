package io.github.daozhanglv.wortschatz.llm;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DeepSeekClient {

    private final RestClient restClient;

    public DeepSeekClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://api.deepseek.com")
                .build();
    }

    public String chat() {
        String apiKey = System.getenv("DEEPSEEK_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("DEEPSEEK_API_KEY is missing");
        }

        String requestBody = """
                {
                  "model": "deepseek-flash",
                  "messages": [
                    {"role": "user", "content": "Please reply with OK."}
                  ]
                }
                """;

        return restClient.post()
                .uri("chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(String.class);
    }
}