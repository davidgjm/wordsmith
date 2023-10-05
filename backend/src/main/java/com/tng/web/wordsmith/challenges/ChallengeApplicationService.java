package com.tng.web.wordsmith.challenges;

import com.tng.web.wordsmith.infrastructure.web.SlicedResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Pageable;

public interface ChallengeApplicationService {

    ChallengeRecordDto save(ChallengeRecordDto dto);

    SlicedResponse<ChallengeRecordDto> findRecords();
    SlicedResponse<ChallengeRecordDto> findRecordsByStemId(@NotNull @Positive Long stemId, Pageable pageRequest);
}
