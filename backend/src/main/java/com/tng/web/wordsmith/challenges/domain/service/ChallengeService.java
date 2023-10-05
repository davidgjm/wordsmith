package com.tng.web.wordsmith.challenges.domain.service;

import com.tng.web.wordsmith.challenges.ChallengeRecordDto;
import com.tng.web.wordsmith.challenges.domain.model.ChallengeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChallengeService {
    ChallengeRecord save(ChallengeRecordDto dto);

    Page<ChallengeRecord> findByStemId(Long stemId, Pageable pageRequest);
}
