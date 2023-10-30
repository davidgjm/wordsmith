package com.tng.web.wordsmith.word.domain.vo;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

import java.util.stream.Stream;

@Getter
public enum PartOfSpeech {
    NOUN("n.", "Noun"),
    ADJECTIVE("adj.", "Adjective"),
    NUMBER("num.", "Number"),
    PRONOUN("pron.", "Pronoun"),
    VERB("v.", "Verb"),
    VERB_TRANSITIVE("vt.", "Transitive verb"),
    VERB_INTRANSITIVE("vi.", "Intransitive verb"),
    VERB_AUXILIARY("v.aux.", "Auxiliary verb"),
    ADVERB("adv.", "Adverb"),
    ARTICLE("art.", "Article"),
    PREPOSITION("prep.", "Preposition"),
    CONJUNCTION("conj", "Conjunction"),
    INTERJECTION("int.","Interjections")
    ;

    private final String code;
    private final String description;

    PartOfSpeech(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static PartOfSpeech resolve(String code) {
        return Stream.of(PartOfSpeech.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new ErrorResponseException(HttpStatus.BAD_REQUEST))
        ;
    }
}
