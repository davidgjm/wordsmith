package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.StemDto;
import com.tng.web.wordsmith.word.StemFullDto;
import com.tng.web.wordsmith.word.domain.model.Stem;
import com.tng.web.wordsmith.word.domain.repository.StemRepository;
import com.tng.web.wordsmith.word.internal.StemDtoFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

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
        var stemOptional = repository.findByTermIgnoreCase(value.getTerm());
        if (stemOptional.isPresent()) {
            var stem = stemOptional.get();
            log.debug("Stem already exists. Given: {}, persisted: {}", value, stem);
            stem.setIpa(value.getIpa());
            stem.setNote(value.getNote());
            return repository.saveAndFlush(stem);
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

    @Override
    public List<Long> pickRandomStemIds(int limit) {
        var start = Instant.now();
        log.info("Picking {} of stem ids at random", limit);
        Set<Long> result = new HashSet<>();
        List<Long> stemIds = repository.findAllIds();
        Random rnd = new Random();
        int idCount = stemIds.size();
        log.info("Total number of stem ids: {}", idCount);
        if (idCount <= limit) {
            log.warn("No enough stems in data store!");
            return stemIds;
        }

        boolean shouldContinue = true;
        int counter = 0;
        while (shouldContinue) {
            int stemIndex = rnd.nextInt(idCount);
            boolean isAdded = result.add(stemIds.get(stemIndex));
            if (isAdded) {
                counter++;
            } else {
                log.trace("Duplicate index found. {}", stemIndex);
            }
            if (counter == limit) {
                shouldContinue = false;
            }
        }

        if (result.size() < limit) {
            log.error("Insufficient number of random stems collected! expect {} was {}", limit, result.size());
        }
        var duration = Duration.between(start, Instant.now());
        log.info("Time took to pick random stems: {}", duration);
        return new ArrayList<>(result);
    }

    @Override
    public Page<Stem> pickRandomStems(int count, Pageable pageable) {
        log.info("Finding {} random stems with page request {}", count, pageable);
        List<Long> pool = pickRandomStemIds(count);
        return repository.findByIdInOrderByCreated(pool, pageable);
    }

    @Override
    public Page<Stem> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<StemFullDto> findStemsByKeyword(String keyword) {
        log.info("Searching stems by keyword {}", keyword);
        return repository.findByTermIgnoreCaseContaining(keyword,Pageable.unpaged())
                .map(StemDtoFactory::recordFrom)
                ;
    }
}
