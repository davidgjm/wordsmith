package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.WordDto;
import com.tng.web.wordsmith.word.domain.model.Word;
import com.tng.web.wordsmith.word.domain.repository.WordRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
@Valid
public class WordServiceImpl implements WordService {
    private final WordRepository repository;
    private final StemService stemService;

    @Override
    public Word addWord(WordDto dto) {
        log.debug("Attempting to save word {}", dto);

        var stem = stemService.save(dto.getStem());
        Word word = new Word();
        word.setStem(stem);
        word.updateFields(dto);
        return repository.saveAndFlush(word);
    }

    @Transactional
    @Override
    public List<WordDto> findWordsByStemId(Long stemId) {
        return repository.findByStemIdOrderByPartOfSpeech(stemId)
                .map(WordDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<WordDto> findWordsByStem(String stem) {
        return repository.findByStemTermIgnoreCaseOrderByPartOfSpeech(stem)
                .map(WordDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Page<WordDto> findWords(Pageable pageable) {
        log.debug("Finding words with provided query parameters: pageable={}", pageable);
        return repository.findAll(pageable != null ? pageable : Pageable.unpaged())
                .map(WordDto::from);
    }

    @Override
    public WordDto save(Word word) {
        return WordDto.from(repository.saveAndFlush(word));

    }

    @Override
    public Optional<Word> findWordById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public List<WordDto> findWordsByStemOrMeaningFuzzy(String keyword) {
        return repository.findByStemTermIgnoreCaseContaining(keyword)
                .map(WordDto::from)
                .collect(Collectors.toList());
    }
}
