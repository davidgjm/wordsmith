package com.tng.web.wordsmith.word.domain.repository;

import com.tng.web.wordsmith.infrastructure.data.BaseRepository;
import com.tng.web.wordsmith.word.domain.model.Word;

import java.util.stream.Stream;

public interface WordRepository extends BaseRepository<Word> {
    Stream<Word> findByStemIdOrderByPartOfSpeech(Long stemId);

    Stream<Word> findByStemTermIgnoreCaseOrderByPartOfSpeech(String stem);


    Stream<Word> findByStemTermIgnoreCaseContaining(String keyword);
}
