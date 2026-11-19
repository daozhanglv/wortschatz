package io.github.daozhanglv.wortschatz.word;

import io.github.daozhanglv.wortschatz.article.Article;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lemma;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PartOfSpeech partOfSpeech;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String plural;

    @Column(nullable = false)
    private String meaning;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(columnDefinition = "text")
    private String exampleSentence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    protected Word() {
    }

    public Word(String lemma, PartOfSpeech partOfSpeech, Gender gender, String plural, String meaning) {
        this.lemma = lemma;
        this.partOfSpeech = partOfSpeech;
        this.gender = gender;
        this.plural = plural;
        this.meaning = meaning;
    }

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getLemma() {
        return lemma;
    }

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPlural() {
        return plural;
    }

    public String getMeaning() {
        return meaning;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getExampleSentence() {
        return exampleSentence;
    }

    public Article getArticle() {
        return article;
    }
}
