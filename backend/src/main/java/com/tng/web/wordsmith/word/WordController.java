package com.tng.web.wordsmith.word;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/words/v1")
@Valid
@RequiredArgsConstructor
public class WordController {
    private final WordApplicationService applicationService;

    @PostMapping
    public WordDto createWord(@RequestBody @NotNull WordDto request) {
        log.info("Received request to create a word. Request details: {}", request);
        var result = applicationService.addWord(request);
        log.debug("Saved word: {}", result);
        return result;
    }
}
