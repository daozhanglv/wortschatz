package io.github.daozhanglv.wortschatz.llm;

import java.util.List;

record ChatCompletionResponse(
        List<Choice> choices
) {
    record Choice(
            ChatMessage message
    ) {
    }
}