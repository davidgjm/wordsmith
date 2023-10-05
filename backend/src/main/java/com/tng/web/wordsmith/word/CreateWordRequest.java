package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.word.internal.BaseWordDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Valid
public final class CreateWordRequest extends BaseWordDto {

    @Positive
    private Long stemId;

}
