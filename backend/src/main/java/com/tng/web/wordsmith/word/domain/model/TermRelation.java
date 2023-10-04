package com.tng.web.wordsmith.word.domain.model;

import com.tng.web.wordsmith.infrastructure.data.BaseEntity;
import com.tng.web.wordsmith.word.domain.vo.TermRelationshipType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uq_term_id", columnNames = {"sourceId","targetId"})
})
public class TermRelation extends BaseEntity {

    @Column(nullable = false)
    private Long sourceId;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TermRelationshipType relationship;
}
