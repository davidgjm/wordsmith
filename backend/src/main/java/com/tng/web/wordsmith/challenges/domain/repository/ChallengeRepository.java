package com.tng.web.wordsmith.challenges.domain.repository;

import com.tng.web.wordsmith.challenges.domain.model.ChallengeRecord;
import com.tng.web.wordsmith.challenges.domain.vo.AttemptResult;
import com.tng.web.wordsmith.infrastructure.data.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface ChallengeRepository extends BaseRepository<ChallengeRecord> {

    Page<ChallengeRecord> findChallengeByResultOrderByAttemptedAt(AttemptResult result, Pageable pageable);

    List<ChallengeRecord> findChallengeByResultAndAttemptedAtAfterOrderByAttemptedAtDesc(AttemptResult result, Instant attemptedAt);


    Page<ChallengeRecord> findByStemIdOrderByAttemptedAtDesc(Long stemId, Pageable pageable);

}
