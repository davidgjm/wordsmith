package com.tng.web.wordsmith.challenges;

import com.tng.web.wordsmith.infrastructure.web.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/challenges/v1")
@Validated
@RequiredArgsConstructor
public class VocabularyChallengeController {
    private final ChallengeApplicationService applicationService;

    @PostMapping
    public ChallengeRecordDto takeChallenge(@RequestBody @Valid ChallengeRecordDto request) {
        log.info("Recording challenge attempt {}", request);
        return applicationService.save(request);
    }

    @GetMapping("/{stemId}")
    public ApiResponse<List<ChallengeRecordDto>> findChallengesByStem(@PathVariable @NotNull @Positive @Valid Long stemId) {
        log.info("Loading all challenge records for stem {}", stemId);
        return applicationService.findRecordsByStemId(stemId);
    }


}
