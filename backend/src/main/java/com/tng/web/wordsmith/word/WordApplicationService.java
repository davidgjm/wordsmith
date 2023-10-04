package com.tng.web.wordsmith.word;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface WordApplicationService {

    WordDto addWord(@NotNull WordDto wordDto);

    List<WordDto> findWordsByStemId(@NotNull Long stemId);
    List<WordDto> findWordsByStem(@NotBlank String stem);
}
