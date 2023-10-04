package com.tng.web.wordsmith.challenges;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/challenges/v1")
@Valid
@RequiredArgsConstructor
public class VocabularyChallengeController {
    private final ChallengeApplicationService applicationService;

    @PostMapping
    public ChallengeRecordDto takeChallenge(@RequestBody @Valid ChallengeRecordDto request) {
        log.info("Recording challenge attempt {}", request);
        return applicationService.save(request);
    }
}
