package com.tng.web.wordsmith.challenges.domain.service;

import com.tng.web.wordsmith.challenges.ChallengeRecordDto;
import com.tng.web.wordsmith.challenges.domain.model.ChallengeRecord;
import com.tng.web.wordsmith.challenges.domain.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Slf4j
@Service
@Validated
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeRepository repository;
    @Override
    public ChallengeRecord save(ChallengeRecordDto dto) {
        return repository.save(ChallengeRecord.from(dto));
    }

}
