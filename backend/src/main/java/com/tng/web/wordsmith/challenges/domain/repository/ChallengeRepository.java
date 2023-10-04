package com.tng.web.wordsmith.challenges.domain.repository;

import com.tng.web.wordsmith.challenges.domain.model.ChallengeRecord;
import com.tng.web.wordsmith.challenges.domain.vo.AttemptResult;
import com.tng.web.wordsmith.infrastructure.data.BaseRepository;

import java.time.Instant;
import java.util.List;

public interface ChallengeRepository extends BaseRepository<ChallengeRecord> {

    List<ChallengeRecord> findChallengeByResultOrderByAttemptedAt(AttemptResult result);

    List<ChallengeRecord> findChallengeByResultAndAttemptedAtAfterOrderByAttemptedAtDesc(AttemptResult result, Instant attemptedAt);



}
