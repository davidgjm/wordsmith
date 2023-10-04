package com.tng.web.wordsmith.word;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tng.web.wordsmith.word.domain.model.Stem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Valid
public final class StemDto {
    @Positive
    private Long id;

    @NotBlank
    @JsonProperty(required = true)
    private String term;

    private String ipa;

    private String note;


    public static StemDto from(Stem stem) {
        var dto = new StemDto();
        dto.setTerm(stem.getTerm());
        dto.setId(stem.getId());
        dto.setIpa(stem.getIpa());
        dto.setNote(stem.getNote());
        return dto;
    }
}
