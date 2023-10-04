package com.tng.web.wordsmith.word.domain.model;

import com.tng.web.wordsmith.infrastructure.data.BaseEntity;
import com.tng.web.wordsmith.word.StemDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Stem extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Setter(value = AccessLevel.PRIVATE)
    private String term;

    @Column(length = 64)
    private String ipa;

    private String note;

    public static Stem from(StemDto dto) {
        var stem = new Stem();
        stem.setId(dto.getId());
        stem.setTerm(dto.getTerm());
        stem.setIpa(dto.getIpa());
        stem.setNote(dto.getNote());
        return stem;
    }
}
