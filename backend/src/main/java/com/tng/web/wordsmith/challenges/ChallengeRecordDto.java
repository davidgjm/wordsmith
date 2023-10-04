package com.tng.web.wordsmith.challenges;

import com.tng.web.wordsmith.challenges.domain.model.ChallengeRecord;
import com.tng.web.wordsmith.challenges.domain.vo.AttemptResult;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.Instant;

@Data
public final class ChallengeRecordDto {
    @Positive
    private Long id;

    @Positive
    @NotNull
    private Long stemId;

    @Positive
    private Long wordId;

    @PastOrPresent
    private Instant attemptedAt;

    @NotNull
    private AttemptResult result;

    private String note;

    private Instant lastModified;

    public static ChallengeRecordDto from(ChallengeRecord value) {
        var dto = new ChallengeRecordDto();
        dto.setId(value.getId());
        dto.setStemId(value.getStemId());
        dto.setWordId(value.getWordId());
        dto.setAttemptedAt(value.getAttemptedAt());
        dto.setResult(value.getResult());
        dto.setNote(value.getNote());
        dto.setLastModified(value.getLastModified());
        return dto;
    }
}
