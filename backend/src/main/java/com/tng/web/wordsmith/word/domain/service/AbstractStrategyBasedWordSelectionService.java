package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.StemDto;
import com.tng.web.wordsmith.word.domain.vo.WordSelectionStrategyType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

@RequiredArgsConstructor
public abstract class AbstractStrategyBasedWordSelectionService implements StrategyBasedWordSelectionService{
    private final WordSelectionStrategyType supportedType;

    @Override
    public boolean isStrategySupported(WordSelectionStrategyType strategyType) {
        Assert.notNull(strategyType,"Provided strategy type cannot be null!");
        return supportedType.equals(strategyType);
    }

    @Override
    public WordSelectionStrategyType getSupportedType() {
        return this.supportedType;
    }
}
