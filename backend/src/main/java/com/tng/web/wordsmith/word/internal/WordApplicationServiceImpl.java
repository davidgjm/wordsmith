package com.tng.web.wordsmith.word.internal;

import com.tng.web.wordsmith.word.WordApplicationService;
import com.tng.web.wordsmith.word.WordDto;
import com.tng.web.wordsmith.word.domain.service.WordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Valid
public class WordApplicationServiceImpl implements WordApplicationService {
    private final WordService service;

    @Override
    public WordDto addWord(WordDto wordDto) {
        log.info("Attempting to added word {}", wordDto);
        var word = service.addWord(wordDto);
        return WordDto.from(word);
    }

    @Override
    public List<WordDto> findWordsByStemId(Long stemId) {
        log.info("Attempting to find words for stem id {}", stemId);
        return service.findWordsByStemId(stemId);
    }

    @Override
    public List<WordDto> findWordsByStem(String stem) {
        log.info("Attempting to find words with stem: {}", stem);
        return service.findWordsByStem(stem);
    }
}
