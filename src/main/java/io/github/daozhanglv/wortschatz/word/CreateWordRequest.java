package io.github.daozhanglv.wortschatz.word;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateWordRequest(
        @NotBlank @Size(max = 100) String lemma,
        @NotNull PartOfSpeech partOfSpeech,
        Gender gender,
        @Size(max = 255) String plural,
        @NotBlank @Size(max = 255) String meaning
) {
}