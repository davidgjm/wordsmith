package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.infrastructure.web.ApiResponse;
import com.tng.web.wordsmith.infrastructure.web.PaginationCriteria;
import com.tng.web.wordsmith.infrastructure.web.SlicedResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/words/v1")
@Valid
@RequiredArgsConstructor
public class WordController {
    private final WordApplicationService applicationService;

    @GetMapping
    public SlicedResponse<WordDto> loadWords(@Valid PaginationCriteria paginationCriteria) {
        log.info("Loading words with page request {}", paginationCriteria);
        if (paginationCriteria == null) {
            log.info("No pagination query parameter provided! Using default values");
            paginationCriteria = new PaginationCriteria();
        }
        var page = applicationService.findWords(paginationCriteria.pageRequest());
        return new SlicedResponse<>(page);
    }

    @PostMapping
    public WordDto createWord(@RequestBody @NotNull @Valid WordDto request) {
        log.info("Received request to create a word. Request details: {}", request);
        var result = applicationService.addWord(request);
        log.debug("Saved word: {}", result);
        return result;
    }

    @GetMapping("/{stemId}")
    public SlicedResponse<WordDto> findWordsByStem(@NotNull @Positive @PathVariable Long stemId) {
        log.info("Loading words for stem {}", stemId);
        var result = applicationService.findWordsByStemId(stemId);
        return SlicedResponse.from(result);
    }

    @PostMapping("/{stemId}")
    public ApiResponse<WordDto> newWord(@NotNull @Positive @PathVariable Long stemId, @NotNull @Valid @RequestBody CreateWordRequest request) {
        log.info("Received create to add new word for stem {}. Request details: {}", stemId, request);
        request.setStemId(stemId);
        return ApiResponse.from(applicationService.addWord(request));
    }
}
