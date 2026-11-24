package io.github.daozhanglv.wortschatz.llm;

import java.util.List;

record ChatCompletionRequest(
        String model,
        List<ChatMessage> messages
) {
}