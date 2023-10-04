package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.StemDto;
import com.tng.web.wordsmith.word.domain.model.Stem;
import com.tng.web.wordsmith.word.domain.repository.StemRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@Slf4j
@RequiredArgsConstructor
@Service
public class StemServiceImpl implements StemService {
    private final StemRepository repository;

    @Override
    public Stem save(Stem value) {
        log.debug("Attempting to save stem: {}", value);
        return repository.save(value);
    }

    @Override
    public Stem save(StemDto value) {
        log.debug("Attempting to save dto value: {}", value);
        if (value.getId() != null) {
            var optional = repository.findById(value.getId())
                    .filter(s -> !s.getTerm().equalsIgnoreCase(value.getTerm().trim()));
            optional.ifPresent(stem -> {
                log.error("A different stem wit the id already exists! {}", stem);
                throw new IllegalStateException("term conflict");
            });
        }

        return repository.saveAndFlush(Stem.from(value));
    }

    @Override
    public Optional<Stem> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Stem> findByStem(String stem) {
        return repository.findByTermIgnoreCase(stem);
    }
}
