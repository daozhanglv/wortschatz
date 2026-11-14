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
        return service.create(request);
    }

    @GetMapping
    public List<WordResponse> list() {
        return service.findAll();
    }
}
