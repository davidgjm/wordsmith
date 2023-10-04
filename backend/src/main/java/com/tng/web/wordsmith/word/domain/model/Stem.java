package com.tng.web.wordsmith.word.domain.model;

import com.tng.web.wordsmith.infrastructure.data.BaseAuditEntity;
import com.tng.web.wordsmith.infrastructure.data.BaseEntity;
import com.tng.web.wordsmith.word.StemDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Stem extends BaseAuditEntity {

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
