package io.github.daozhanglv.wortschatz.word;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final WordRepository repository;

    public WordService(WordRepository repository) {
        this.repository = repository;
    }

    public WordResponse create(CreateWordRequest request) {
        Word word = new Word(request.lemma(), request.partOfSpeech(), request.gender(),
                request.plural(), request.meaning());

        Word saved = repository.save(word);

        return WordResponse.from(saved);
    }

    public List<WordResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(WordResponse::from)
                .toList();
    }
}
