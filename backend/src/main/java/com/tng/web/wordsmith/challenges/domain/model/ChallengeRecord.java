package com.tng.web.wordsmith.challenges.domain.model;

import com.tng.web.wordsmith.challenges.ChallengeRecordDto;
import com.tng.web.wordsmith.challenges.domain.vo.AttemptResult;
import com.tng.web.wordsmith.infrastructure.data.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ChallengeRecord extends BaseEntity {

    @Positive
    @Column(nullable = false, updatable = false)
    private Long stemId;

    @CreatedDate
    private Instant attemptedAt;


    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 64)
    private AttemptResult result;

    private String note;

    public static ChallengeRecord from(ChallengeRecordDto value) {
        var record = new ChallengeRecord();
        record.setId(value.getId());
        record.setStemId(value.getStemId());
        record.setResult(value.getResult());
        record.setNote(value.getNote());
        return record;
    }
}
