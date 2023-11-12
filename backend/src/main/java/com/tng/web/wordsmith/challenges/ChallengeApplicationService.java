package com.tng.web.wordsmith.challenges;

import com.tng.web.wordsmith.infrastructure.web.ApiResponse;
import com.tng.web.wordsmith.infrastructure.web.SlicedResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface ChallengeApplicationService {

    ChallengeRecordDto save(ChallengeRecordDto dto);

    SlicedResponse<ChallengeRecordDto> findRecords();
    ApiResponse<List<ChallengeRecordDto>> findRecordsByStemId(@NotNull @Positive Long stemId);
}
