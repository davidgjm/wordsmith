package com.tng.web.wordsmith.word;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tng.web.wordsmith.word.domain.model.Stem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.Instant;

@Data
@Valid
public final class StemDto {
    @Positive
    private Long id;

    @NotBlank
    @JsonProperty(required = true)
    private String term;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String ipa;

    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Past
    private Instant created;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Past
    private Instant lastModified;

    public static StemDto from(Stem stem) {
        var dto = new StemDto();
        dto.setTerm(stem.getTerm());
        dto.setId(stem.getId());
        dto.setIpa(stem.getIpa());
        dto.setNote(stem.getNote());
        dto.setCreated(stem.getCreated());
        dto.setLastModified(stem.getLastModified());
        return dto;
    }
}
