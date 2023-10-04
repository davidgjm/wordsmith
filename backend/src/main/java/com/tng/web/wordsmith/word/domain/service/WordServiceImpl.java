package com.tng.web.wordsmith.word.domain.service;

import com.tng.web.wordsmith.word.WordDto;
import com.tng.web.wordsmith.word.domain.model.Word;
import com.tng.web.wordsmith.word.domain.repository.WordRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
        word.updateProperties(dto);
        return repository.saveAndFlush(word);
    }

    @Override
    public List<WordDto> findWordsByStemId(Long stemId) {
        return repository.findByStemIdOrderByPartOfSpeech(stemId)
                .map(WordDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<WordDto> findWordsByStem(String stem) {
        return repository.findByStemTermIgnoreCaseOrderByPartOfSpeech(stem)
                .map(WordDto::from)
                .collect(Collectors.toList());
    }
}
