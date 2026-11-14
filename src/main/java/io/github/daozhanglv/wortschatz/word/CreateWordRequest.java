package io.github.daozhanglv.wortschatz.word;

public record CreateWordRequest(
        String lemma,
        PartOfSpeech partOfSpeech,
        Gender gender,
        String plural,
        String meaning
) {
}