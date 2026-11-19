package io.github.daozhanglv.wortschatz.article;

import java.time.Instant;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        Instant createdAt) {
    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt()
        );
    }
}
