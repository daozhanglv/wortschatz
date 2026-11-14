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
        // 1. 把请求变成实体
        Word word = new Word(request.lemma(), request.partOfSpeech(), request.gender(),
                request.plural(), request.meaning());

        // 2. 存进数据库：调用 repository 的 save 方法，把 word 传进去。
        //    它会返回保存后的 Word，用一个变量接住，比如叫 saved
        Word saved = repository.save(word);

        // 3. 把 saved 转成 WordResponse 再返回：用你上一步写的 from 方法
        WordResponse response = WordResponse.from(saved);
        return response;
    }

    public List<WordResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(WordResponse::from)
                .toList();
    }
}
