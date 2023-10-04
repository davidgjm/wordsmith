package com.tng.web.wordsmith.challenges.domain.repository;

import com.tng.web.wordsmith.challenges.domain.model.ChallengeStatistics;
import com.tng.web.wordsmith.infrastructure.data.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeStatisticsRepository extends BaseRepository<ChallengeStatistics> {

    List<ChallengeStatistics> findAllByStemIdOrderByState(Long stemId);

    @Query("select c from ChallengeStatistics c order by c.count desc limit 10")
    List<ChallengeStatistics> findTop10();
}
