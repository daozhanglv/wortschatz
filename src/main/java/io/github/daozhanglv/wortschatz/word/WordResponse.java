package io.github.daozhanglv.wortschatz.word;

import java.time.Instant;

public record WordResponse(
        Long id,
        String lemma,
        PartOfSpeech partOfSpeech,
        Gender gender,
        String plural,
        String meaning,
        Instant createdAt
) {
    public static WordResponse from(Word word) {
        return new WordResponse(
                word.getId(),
                word.getLemma(),
                word.getPartOfSpeech(),
                word.getGender(),
                word.getPlural(),
                word.getMeaning(),
                word.getCreatedAt()
        );
    }
}