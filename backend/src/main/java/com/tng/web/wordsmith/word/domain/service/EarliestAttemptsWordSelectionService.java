package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.StemDto;
import com.tng.web.wordsmith.word.domain.vo.WordSelectionStrategyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class EarliestAttemptsWordSelectionService extends AbstractStrategyBasedWordSelectionService{
    private static final WordSelectionStrategyType STRATEGY_TYPE = WordSelectionStrategyType.EARLIEST;

    public EarliestAttemptsWordSelectionService() {
        super(STRATEGY_TYPE);
    }


    @Override
    public Page<StemDto> selectWords(Pageable pageRequest) {
        return null;
    }
}
