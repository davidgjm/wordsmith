package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.word.domain.vo.WordSelectionStrategyType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StemApplicationService {
    Page<StemDto> findAll(Pageable pageable);
    Page<StemDto> findRandom(@Positive int limit, Pageable pageable);

    Page<StemDto> findStemsByStrategy(String strategy, Pageable pageable);

}
