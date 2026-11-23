package io.github.daozhanglv.wortschatz;

import io.github.daozhanglv.wortschatz.llm.DeepSeekClient;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

class DeepSeekClientIT {

    @Test
    void callsDeepSeek() {
        DeepSeekClient client = new DeepSeekClient(RestClient.builder());
        String response = client.chat();
        System.out.println(response);
    }
}