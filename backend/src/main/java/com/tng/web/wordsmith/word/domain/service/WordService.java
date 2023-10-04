package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.WordDto;
import com.tng.web.wordsmith.word.domain.model.Word;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface WordService {

    Word addWord(@NotNull WordDto dto);

    List<WordDto> findWordsByStemId(Long stemId);

    List<WordDto> findWordsByStem(String stem);
}
