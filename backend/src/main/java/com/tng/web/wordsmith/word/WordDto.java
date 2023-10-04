package com.tng.web.wordsmith.word;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tng.web.wordsmith.word.domain.model.Word;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
    private String meaning;
    private String explanation;
    private String example;

    public static WordDto from(@NotNull @Valid Word word) {
        StemDto stemDto = StemDto.from(word.getStem());
        var dto = new WordDto();
        dto.setId(word.getId());
        dto.setStem(stemDto);
        dto.setPartOfSpeech(word.getPartOfSpeech().getCode());
        dto.setVariants(word.getVariants());
        dto.setMeaning(word.getMeaning());
        dto.setExplanation(word.getExplanation());
        dto.setExample(word.getExample());
        return dto;
    }
}
