package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.word.internal.BaseWordDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Valid
public final class UpdateWordRequest extends BaseWordDto {

    @Positive
    @NotNull
    private Long id;
}
