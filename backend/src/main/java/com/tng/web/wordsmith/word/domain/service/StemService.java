package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.StemDto;
import com.tng.web.wordsmith.word.domain.model.Stem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Optional;

public interface StemService {


    Stem save(@NotNull @Valid Stem value);
    Stem save(@NotNull @Valid StemDto value);

    Optional<Stem> findById(@NotNull @Positive Long id);

    Optional<Stem> findByStem(@NotBlank String stem);
}
