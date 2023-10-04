package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.infrastructure.web.PagedResponse;
import com.tng.web.wordsmith.infrastructure.web.PaginationCriteria;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/words/v1")
@Valid
@RequiredArgsConstructor
public class WordController {
    private final WordApplicationService applicationService;

    @GetMapping
    public PagedResponse<WordDto> loadWords(@Valid PaginationCriteria paginationCriteria) {
        log.info("Loading words with page request {}", paginationCriteria);
        if (paginationCriteria == null) {
            log.info("No pagination query parameter provided! Using default values");
            paginationCriteria = new PaginationCriteria();
        }
        var page = applicationService.findWords(paginationCriteria.pageRequest());
        return new PagedResponse<>(page);
    }

    @PostMapping
    public WordDto createWord(@RequestBody @NotNull @Valid WordDto request) {
        log.info("Received request to create a word. Request details: {}", request);
        var result = applicationService.addWord(request);
        log.debug("Saved word: {}", result);
        return result;
    }

}
