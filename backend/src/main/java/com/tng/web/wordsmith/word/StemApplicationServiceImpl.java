package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.infrastructure.config.AppProperties;
import com.tng.web.wordsmith.word.domain.service.StemService;
import com.tng.web.wordsmith.word.domain.vo.WordSelectionStrategyType;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Slf4j
@Service
@Validated
public class StemApplicationServiceImpl implements StemApplicationService {
    private final StemService service;
    private final AppProperties appProperties;

    @Override
    public Page<StemDto> findAll(Pageable pageable) {
        return service.findAll(pageable).map(StemDto::from);
    }

    @Override
    public Page<StemDto> findRandom(int limit, Pageable pageable) {
        return service.pickRandomStems(limit, pageable).map(StemDto::from);
    }

    @Override
    public Page<StemDto> findStemsByStrategy(String strategy, Pageable pageable) {
        log.info("Attempting to find stems with strategy {}", strategy);
        WordSelectionStrategyType strategyType = null;
        if (!StringUtils.hasText(strategy)) {
            log.debug("Provided strategy is empty. Using default value...");
            strategyType = WordSelectionStrategyType.DEFAULT;
        } else {
            strategyType = WordSelectionStrategyType.resolve(strategy);
        }

        Page<StemDto> result = Page.empty(pageable);
        switch (strategyType) {
            case DEFAULT -> result = service.findAll(pageable).map(StemDto::from);
            case RANDOM ->
                    result = service.pickRandomStems(appProperties.getStemSelectionSize(), pageable).map(StemDto::from);
            case EARLIEST -> throw new UnsupportedOperationException("Not implemented!");
            case MOST_FAILED_ATTEMPTS -> throw new UnsupportedOperationException("Not implemented!");
        }
        return result;
    }


}
