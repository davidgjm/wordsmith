package com.tng.web.wordsmith.challenges.internal;

import com.tng.web.wordsmith.challenges.ChallengeApplicationService;
import com.tng.web.wordsmith.challenges.ChallengeRecordDto;
import com.tng.web.wordsmith.challenges.domain.service.ChallengeService;
import com.tng.web.wordsmith.infrastructure.web.PagedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public PagedResponse<ChallengeRecordDto> findRecords() {
        return null;
    }
}
