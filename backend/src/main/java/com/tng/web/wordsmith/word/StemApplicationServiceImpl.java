package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.infrastructure.config.AppProperties;
import com.tng.web.wordsmith.word.domain.service.StemService;
import com.tng.web.wordsmith.word.domain.vo.WordSelectionStrategyType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponseException;

@RequiredArgsConstructor
@Slf4j
@Service
@Validated
public class StemApplicationServiceImpl implements StemApplicationService {
    private final StemService service;
    private final AppProperties appProperties;

    @Override
    public StemDto create(StemDto data) {
        log.info("Attempting to create new stem with data {}", data);
        return StemDto.from(service.save(data));
    }

    @Override
    public StemDto update(StemDto requestBody) {
        log.info("Attempting to update existing stem with data. {}", requestBody);
        var stem = service.findById(requestBody.getId()).orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
        stem.setIpa(requestBody.getIpa());
        stem.setNote(requestBody.getNote());
        stem.setNote(requestBody.getNote());
        return StemDto.from(service.save(stem));
    }

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

    @Override
    public StemDto findStem(Long stemId) {
        return StemDto.from(service.findById(stemId).orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND)));
    }


}
