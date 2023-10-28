package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.StemDto;
import com.tng.web.wordsmith.word.StemFullDto;
import com.tng.web.wordsmith.word.domain.model.Stem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StemService {


    Stem save(@NotNull @Valid Stem value);
    Stem save(@NotNull @Valid StemDto value);

    Optional<Stem> findById(@NotNull @Positive Long id);

    Optional<Stem> findByStem(@NotBlank String stem);

    List<Long> pickRandomStemIds(@Positive int count);

    Page<Stem> pickRandomStems(@Positive int count, Pageable pageable);

    Page<Stem> findAll(Pageable pageable);

    Page<StemFullDto> findStemsByKeyword(@NotBlank String keyword);
}
