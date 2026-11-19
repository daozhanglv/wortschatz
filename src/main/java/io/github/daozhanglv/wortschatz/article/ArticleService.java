package io.github.daozhanglv.wortschatz.article;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public ArticleResponse create(CreateArticleRequest request) {
        Article article = new Article(request.title(), request.content());

        Article saved = repository.save(article);

        return ArticleResponse.from(saved);
    }

    public List<ArticleResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(ArticleResponse::from)
                .toList();
    }
}