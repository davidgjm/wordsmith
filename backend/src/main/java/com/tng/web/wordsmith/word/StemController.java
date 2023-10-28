package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.infrastructure.web.ApiResponse;
import com.tng.web.wordsmith.infrastructure.web.PaginationCriteria;
import com.tng.web.wordsmith.infrastructure.web.SlicedResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/stems/v1")
@Validated
@RequiredArgsConstructor
public class StemController {
    private final StemApplicationService applicationService;
    private final WordApplicationService wordApplicationService;

    @GetMapping
    public SlicedResponse<StemDto> findStems(@Valid PaginationCriteria paginationCriteria, @RequestParam(value = "strategy", required = false) String strategy) {
        log.info("Loading stems with page request {}. Optional strategy {}", paginationCriteria, strategy);
        var page = applicationService.findStemsByStrategy(strategy, paginationCriteria.pageRequest());
        return SlicedResponse.from(page);
    }

    @PostMapping
    public ApiResponse<StemDto> createStem(@NotNull @Valid @RequestBody StemDto requestBody) {
        log.info("Received request to create new stem. Request details: {}", requestBody);
        var result = applicationService.create(requestBody);
        return ApiResponse.from(result);
    }

    @GetMapping("/{stemId}")
    public ApiResponse<StemDto> getStem(@NotNull @Positive @PathVariable Long stemId) {
        log.info("Loading stem details for {}", stemId);
        return ApiResponse.from(applicationService.findStem(stemId));
    }
    @PutMapping("/{stemId}")
    public ApiResponse<StemDto> updateStem(@NotNull @Positive @PathVariable Long stemId, @NotNull @Valid @RequestBody StemDto requestBody) {
        log.info("Received request to update existing stem. Request details: {}", requestBody);
        requestBody.setId(stemId);
        StemDto result = applicationService.update(requestBody);
        return ApiResponse.from(result);
    }


    @GetMapping("/{stemId}/words")
    public SlicedResponse<WordDto> loadWords(@NotNull @Positive @PathVariable Long stemId) {
        log.info("Received request to load words for stem {}", stemId);
        var result = wordApplicationService.findWordsByStemId(stemId);
        return SlicedResponse.from(result);
    }

    @PostMapping("/{stemId}/words")
    public ApiResponse<WordDto> newWord(@NotNull @Positive @PathVariable Long stemId, @NotNull @Valid @RequestBody CreateWordRequest request) {
        log.info("Received create to add new word for stem {}. Request details: {}", stemId, request);
        request.setStemId(stemId);
        return ApiResponse.from(wordApplicationService.addWord(request));
    }

}
