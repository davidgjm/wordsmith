package com.tng.web.wordsmith.word.domain.repository;

import com.tng.web.wordsmith.infrastructure.data.BaseRepository;
import com.tng.web.wordsmith.word.domain.model.Stem;

import java.util.Optional;

public interface StemRepository extends BaseRepository<Stem> {

    Optional<Stem> findByStemIgnoreCase(String stem);
}
