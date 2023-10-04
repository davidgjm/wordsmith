package com.tng.web.wordsmith.challenges.domain.model;

import com.tng.web.wordsmith.challenges.domain.vo.AttemptResult;
import com.tng.web.wordsmith.infrastructure.data.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "uq_stem_state", columnNames = {"stemId", "state"})
)
public class ChallengeStatistics extends BaseEntity {
    @Positive
    @Column(nullable = false, updatable = false)
    private Long stemId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private AttemptResult state;

    @PositiveOrZero
    private Long count;

    @LastModifiedDate
    private Instant lastModified;


    @Transient
    public void increment() {
        this.count += 1;
    }

}
