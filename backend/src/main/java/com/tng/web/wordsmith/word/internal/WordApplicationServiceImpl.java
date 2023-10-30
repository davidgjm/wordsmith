package com.tng.web.wordsmith.word.internal;

import com.tng.web.wordsmith.word.*;
import com.tng.web.wordsmith.word.domain.model.Stem;
import com.tng.web.wordsmith.word.domain.model.Word;
import com.tng.web.wordsmith.word.domain.service.StemService;
import com.tng.web.wordsmith.word.domain.service.WordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Valid
public class WordApplicationServiceImpl implements WordApplicationService {
    private final WordService service;
    private final StemService stemService;

    @Override
    public WordDto addWord(WordDto wordDto) {
        log.info("Attempting to add word {}", wordDto);
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

    @Override
    public Page<WordDto> findWords(Pageable pageRequest) {
        log.info("Finding all words with page request {}", pageRequest);
        return service.findWords(pageRequest);
    }

    @Override
    public WordDto addWord(CreateWordRequest request) {
        log.info("Creating new word: {}", request);
        var stemId = request.getStemId();
        Stem stem = stemService.findById(stemId).orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
        var word = new Word();
        word.setStem(stem);
        word.updateFields(request);
        return service.save(word);
    }

    @Override
    public WordDto update(UpdateWordRequest request) {
        log.info("Attempting to update word. Request details: {}", request);
        Word word = findWord(request.getId());
        word.updateFields(request);
        return service.save(word);
    }

    @Override
    public WordDto findWordById(Long wordId) {
        return WordDto.from(findWord(wordId));
    }

    @Override
    public Page<StemFullDto> findWordsByStemFuzzy(String keyword) {
        log.info("Attempting to find words with stem/meaning like {}", keyword);
        return stemService.findStemsByKeyword(keyword);
    }

    private Word findWord(Long wordId) {
        return service.findWordById(wordId).orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
    }
}
