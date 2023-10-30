package com.tng.web.wordsmith.word;

import jakarta.validation.constraints.NotBlank;

public record PartOfSpeechDto(
        @NotBlank
        String code,
        String description
) {
}
