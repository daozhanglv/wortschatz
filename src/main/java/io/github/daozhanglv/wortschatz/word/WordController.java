package io.github.daozhanglv.wortschatz.word;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")

public class WordController {
    private final WordService service;

    public WordController(WordService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordResponse create(@RequestBody CreateWordRequest request) {
        // 只有一行：调用 service 的 create 方法，把 request 传进去，并把结果 return 出去
        WordResponse response = service.create(request);
        return response;
    }

    @GetMapping
    public List<WordResponse> list() {
        List<WordResponse> list = service.findAll();
        return list;
    }
}
