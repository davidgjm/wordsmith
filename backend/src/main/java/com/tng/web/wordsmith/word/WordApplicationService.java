package com.tng.web.wordsmith.word;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WordApplicationService {

    WordDto addWord(@NotNull @Valid WordDto wordDto);

    List<WordDto> findWordsByStemId(@NotNull Long stemId);
    List<WordDto> findWordsByStem(@NotBlank String stem);

    Page<WordDto> findWords(Pageable pageRequest);

    WordDto addWord(CreateWordRequest request);
    WordDto update(@NotNull @Valid UpdateWordRequest request);

    WordDto findWordById(@NotNull @Positive Long wordId);

    List<WordDto> findWordsByStemOrTranslation(@NotBlank String keyword);
}
