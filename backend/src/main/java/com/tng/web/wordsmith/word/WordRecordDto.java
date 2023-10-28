package com.tng.web.wordsmith.word;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
import java.util.List;


@Valid
public record WordRecordDto(
        @Positive
        Long id,
        @NotBlank
        @JsonProperty(required = true)
        String partOfSpeech,
        String ipa,
        List<String> variants,
        @NotBlank
        @JsonProperty(required = true)
        String translation,
        String explanation,
        String example,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Instant created,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Instant lastModified
) {
}
