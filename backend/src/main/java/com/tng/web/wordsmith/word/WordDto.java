package com.tng.web.wordsmith.word;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tng.web.wordsmith.word.domain.model.Word;
import com.tng.web.wordsmith.word.internal.BaseWordDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@Valid
public final class WordDto extends BaseWordDto {
    private Long id;

    @JsonProperty(required = true)
    @NotNull
    private StemDto stem;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant created;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant lastModified;


    public static WordDto from(@NotNull @Valid Word word) {
        var dto = new WordDto();
        BeanUtils.copyProperties(word,dto);
        StemDto stemDto = StemDto.from(word.getStem());
        dto.setId(word.getId());
        dto.setStem(stemDto);
        dto.setPartOfSpeech(word.getPartOfSpeech().getCode());
        dto.setCreated(word.getCreated());
        dto.setLastModified(word.getLastModified());
        return dto;
    }
}
