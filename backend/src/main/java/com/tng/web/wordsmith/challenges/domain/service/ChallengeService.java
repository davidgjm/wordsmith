package com.tng.web.wordsmith.challenges.domain.service;

import com.tng.web.wordsmith.challenges.ChallengeRecordDto;
import com.tng.web.wordsmith.challenges.domain.model.ChallengeRecord;

public interface ChallengeService {
    ChallengeRecord save(ChallengeRecordDto dto);
}
