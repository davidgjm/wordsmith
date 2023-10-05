package com.tng.web.wordsmith.challenges.internal;

import com.tng.web.wordsmith.challenges.ChallengeApplicationService;
import com.tng.web.wordsmith.challenges.ChallengeRecordDto;
import com.tng.web.wordsmith.challenges.domain.service.ChallengeService;
import com.tng.web.wordsmith.infrastructure.web.SlicedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Slf4j
@Service
@Validated
public class ChallengeApplicationServiceImpl implements ChallengeApplicationService {
    private final ChallengeService service;
    @Override
    public ChallengeRecordDto save(ChallengeRecordDto dto) {
        log.info("Attempting to save dto {}", dto);
        var value = service.save(dto);
        return ChallengeRecordDto.from(value);
    }

    @Override
    public SlicedResponse<ChallengeRecordDto> findRecords() {
        return null;
    }

    @Override
    public SlicedResponse<ChallengeRecordDto> findRecordsByStemId(Long stemId, Pageable pageRequest) {
        log.info("Finding all challenge records for stem {} with page request {}", stemId, pageRequest);
        var page = service.findByStemId(stemId, pageRequest).map(ChallengeRecordDto::from);
        return new SlicedResponse<>(page);
    }
}
