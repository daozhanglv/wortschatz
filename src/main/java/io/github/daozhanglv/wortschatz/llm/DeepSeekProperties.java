package io.github.daozhanglv.wortschatz.llm;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "deepseek")
public record DeepSeekProperties(
        String baseUrl,
        String model,
        String apiKey
) {
}