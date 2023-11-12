package com.tng.web.wordsmith.challenges.internal;

import com.tng.web.wordsmith.challenges.ChallengeApplicationService;
import com.tng.web.wordsmith.challenges.ChallengeRecordDto;
import com.tng.web.wordsmith.challenges.domain.service.ChallengeService;
import com.tng.web.wordsmith.infrastructure.web.ApiResponse;
import com.tng.web.wordsmith.infrastructure.web.SlicedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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
    public ApiResponse<List<ChallengeRecordDto>> findRecordsByStemId(Long stemId) {
        log.info("Finding all challenge records for stem {}", stemId);
        var page = service.findByStemId(stemId, Pageable.unpaged()).map(ChallengeRecordDto::from);
        return ApiResponse.from(page.getContent());
    }
}
