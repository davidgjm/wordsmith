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
@CrossOrigin
@RestController
@RequestMapping("/api/words/v1")
@Valid
@RequiredArgsConstructor
public class WordController {
    private final WordApplicationService applicationService;

    @GetMapping
    public SlicedResponse<WordDto> loadWords(@Valid PaginationCriteria paginationCriteria, @RequestParam(required = false) @Positive Long stemId) {
        log.info("Loading words with stem id #{} page request {}", stemId, paginationCriteria);
        if (paginationCriteria == null) {
            log.info("No pagination query parameter provided! Using default values");
            paginationCriteria = new PaginationCriteria();
        }
        if (stemId == null) {
            var page = applicationService.findWords(paginationCriteria.pageRequest());

            return new SlicedResponse<>(page);
        } else {
            log.info("Loading words for stem {}", stemId);
            var result = applicationService.findWordsByStemId(stemId);
            return SlicedResponse.from(result);
        }
    }

    @PostMapping
    public WordDto createWord(@RequestBody @NotNull @Valid WordDto request) {
        log.info("Received request to create a word. Request details: {}", request);
        var result = applicationService.addWord(request);
        log.debug("Saved word: {}", result);
        return result;
    }

    @GetMapping("/{wordId}")
    public ApiResponse<WordDto> getWordDetails(@NotNull @Positive @PathVariable Long wordId) {
        log.info("Loading word details for {}", wordId);
        var result = applicationService.findWordById(wordId);
        return ApiResponse.from(result);
    }


    @PutMapping("/{wordId}")
    public ApiResponse<WordDto> updateWord(@NotNull @Positive @PathVariable Long wordId, @NotNull @Valid @RequestBody UpdateWordRequest request) {
        log.info("Updating word for {}", wordId);
        request.setWordId(wordId);
        return ApiResponse.from(applicationService.update(request));
    }
}
