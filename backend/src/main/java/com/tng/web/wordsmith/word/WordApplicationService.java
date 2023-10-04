package com.tng.web.wordsmith.word;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WordApplicationService {

    WordDto addWord(@NotNull @Valid WordDto wordDto);

    List<WordDto> findWordsByStemId(@NotNull Long stemId);
    List<WordDto> findWordsByStem(@NotBlank String stem);

    Page<WordDto> findWords(Pageable pageRequest);
}
