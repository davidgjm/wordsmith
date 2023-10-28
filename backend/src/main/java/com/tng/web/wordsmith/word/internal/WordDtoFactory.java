package com.tng.web.wordsmith.word.internal;

import com.tng.web.wordsmith.word.WordRecordDto;
import com.tng.web.wordsmith.word.domain.model.Word;

public final class WordDtoFactory {
    private WordDtoFactory() {
        throw new UnsupportedOperationException("unexpected call!");
    }

    public static WordRecordDto recordFrom(Word word) {
        return new WordRecordDto(
                word.getId(),
                word.getPartOfSpeech().getCode(),
                word.getIpa(),
                word.getVariants(),
                word.getTranslation(),
                word.getExplanation(),
                word.getExample(),
                word.getCreated(),
                word.getLastModified()
        );
    }
}
