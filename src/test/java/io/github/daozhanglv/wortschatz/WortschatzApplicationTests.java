package io.github.daozhanglv.wortschatz;

import io.github.daozhanglv.wortschatz.article.Article;
import io.github.daozhanglv.wortschatz.article.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class WortschatzApplicationTests {
    @Container
    @ServiceConnection
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17");

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void savesAndReadsArticle() {
        Article article = new Article("A Walk", "Heute gehe ich spazieren.");
        Article savedArticle = articleRepository.saveAndFlush(article);

        Article loadedArticle = articleRepository.findById(savedArticle.getId()).orElseThrow();
        assertEquals("Heute gehe ich spazieren.", loadedArticle.getContent());
    }

}
