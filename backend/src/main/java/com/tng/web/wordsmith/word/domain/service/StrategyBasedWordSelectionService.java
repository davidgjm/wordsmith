package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.StemDto;
import com.tng.web.wordsmith.word.domain.vo.WordSelectionStrategyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StrategyBasedWordSelectionService {
    boolean isStrategySupported(WordSelectionStrategyType strategyType);
    WordSelectionStrategyType getSupportedType();

    Page<StemDto> selectWords(Pageable pageRequest);
}
