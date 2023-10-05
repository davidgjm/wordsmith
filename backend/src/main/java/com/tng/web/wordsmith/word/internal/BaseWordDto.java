package com.tng.web.wordsmith.word.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public abstract class BaseWordDto {
    @NotBlank
    @JsonProperty(required = true)
    private String partOfSpeech;
    private String ipa;
    private List<String> variants;

    @NotBlank
    @JsonProperty(required = true)
    private String translation;
    private String explanation;
    private String example;

}
