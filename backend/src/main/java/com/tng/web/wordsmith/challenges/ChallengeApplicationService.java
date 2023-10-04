package com.tng.web.wordsmith.challenges;

import com.tng.web.wordsmith.infrastructure.web.PagedResponse;

public interface ChallengeApplicationService {

    ChallengeRecordDto save(ChallengeRecordDto dto);

    PagedResponse<ChallengeRecordDto> findRecords();
}
