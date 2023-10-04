package com.tng.web.wordsmith.challenges.domain.model;

import com.tng.web.wordsmith.challenges.domain.vo.AttemptResult;
import com.tng.web.wordsmith.infrastructure.data.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
@Entity
public class ChallengeRecord extends BaseEntity {

    @Positive
    @Column(nullable = false, updatable = false)
    private Long stemId;

    private Long wordId;

    @CreatedDate
    private Instant attemptedAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 64)
    private AttemptResult result;

    private String note;
}
