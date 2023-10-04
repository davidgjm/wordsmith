package com.tng.web.wordsmith.infrastructure.data;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum PartOfSpeech {
    NOUN("n.", "Noun"),
    PRONOUN("pron.", "pronoun"),
    VERB_TRANSITIVE("vt.", "transitive verb"),
    VERB_INTRANSITIVE("vi.", "intransitive verb"),
    ADJECTIVE("adj.", "adjective"),
    ADVERB("adv.", "Adverb"),
    PREPOSITION("prep.", "PREPOSITION"),
    CONJUNCTION("conj", "CONJUNCTION"),
    INTERJECTION("inter","INTERJECTION")
    ;

    private final String code;
    private final String fullForm;

    PartOfSpeech(String code, String fullForm) {
        this.code = code;
        this.fullForm = fullForm;
    }

    public static PartOfSpeech resolve(String code) {
        return Stream.of(PartOfSpeech.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
        ;
    }
}
