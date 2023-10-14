package com.tng.web.wordsmith.word;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StemApplicationService {
    StemDto create(@NotNull @Valid StemDto data);

    StemDto update(@NotNull @Valid StemDto requestBody);

    Page<StemDto> findAll(Pageable pageable);
    Page<StemDto> findRandom(@Positive int limit, Pageable pageable);

    Page<StemDto> findStemsByStrategy(String strategy, Pageable pageable);


    StemDto findStem(@NotNull @Positive Long stemId);
}
