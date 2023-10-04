package com.tng.web.wordsmith.word;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tng.web.wordsmith.word.domain.model.Word;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Valid
public final class WordDto {
    private Long id;

    @JsonProperty(required = true)
    @NotNull
    private StemDto stem;

    @NotBlank
    @JsonProperty(required = true)
    private String partOfSpeech;

    private List<String> variants;

    @NotBlank
    @JsonProperty(required = true)
    private String translation;
    private String explanation;
    private String example;

    private Instant created;
    private Instant lastModified;


    public static WordDto from(@NotNull @Valid Word word) {
        StemDto stemDto = StemDto.from(word.getStem());
        var dto = new WordDto();
        dto.setId(word.getId());
        dto.setStem(stemDto);
        dto.setPartOfSpeech(word.getPartOfSpeech().getCode());
        dto.setVariants(word.getVariants());
        dto.setTranslation(word.getTranslation());
        dto.setExplanation(word.getExplanation());
        dto.setExample(word.getExample());
        dto.setCreated(word.getCreated());
        dto.setLastModified(word.getLastModified());
        return dto;
    }
}
