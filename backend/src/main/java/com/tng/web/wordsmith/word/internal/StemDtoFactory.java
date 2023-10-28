package com.tng.web.wordsmith.word.internal;

import com.tng.web.wordsmith.word.StemFullDto;
import com.tng.web.wordsmith.word.domain.model.Stem;
import jakarta.validation.Valid;

import java.util.stream.Collectors;

@Valid
public final class StemDtoFactory {
    private StemDtoFactory() {
        throw new UnsupportedOperationException();
    }

    public static StemFullDto recordFrom(@Valid Stem stem) {
        return new StemFullDto(
                stem.getId(),
                stem.getTerm(), stem.getIpa(), stem.getNote(),
                stem.getWords().stream().map(WordDtoFactory::recordFrom).collect(Collectors.toList()),
                stem.getCreated(),
                stem.getLastModified()
        );
    }
}
