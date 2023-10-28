package com.tng.web.wordsmith.word;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
import java.util.List;

public record StemFullDto(@Positive Long id,
                          @JsonProperty(required = true) @NotBlank String term,

                          @JsonInclude(value = JsonInclude.Include.NON_EMPTY) String ipa,
                          String note,
                          List<WordRecordDto> words,
                          @Past Instant created,
                          @Past Instant lastModified) {
}
