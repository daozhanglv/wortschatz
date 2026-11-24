package io.github.daozhanglv.wortschatz;

import io.github.daozhanglv.wortschatz.llm.DeepSeekClient;
import io.github.daozhanglv.wortschatz.llm.DeepSeekProperties;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DeepSeekClientIT {

    @Test
    void callsDeepSeek() {
        DeepSeekClient client = new DeepSeekClient(RestClient.builder(), new DeepSeekProperties("https://api.deepseek.com",
                "deepseek-chat", System.getenv("DEEPSEEK_API_KEY")));

        String instruction = "Pick one German word worth learning for a B2 learner from this sentence and explain it briefly: ";

        String firstReply = client.chat(instruction + "Das deutsche Mietrecht gilt im europäischen Vergleich als besonders mieterfreundlich.");
        assertFalse(firstReply.isBlank());
        System.out.println("Reply 1:\n" + firstReply);

        String secondReply = client.chat(instruction + "Der Zugbegleiter informierte die Fahrgäste, dass der lokale Verkehrsbetrieb die Preise erhöht.");
        assertFalse(secondReply.isBlank());
        System.out.println("Reply 2:\n" + secondReply);
    }
}