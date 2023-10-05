package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.infrastructure.web.SlicedResponse;
import com.tng.web.wordsmith.infrastructure.web.PaginationCriteria;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/stems/v1")
@Valid
@RequiredArgsConstructor
public class StemController {
    private final StemApplicationService applicationService;

    @GetMapping
    public SlicedResponse<StemDto> findStems(@Valid PaginationCriteria paginationCriteria, @RequestParam(value = "strategy", required = false) String strategy) {
        log.info("Loading stems with page request {}. Optional strategy {}", paginationCriteria, strategy);
        var page = applicationService.findStemsByStrategy(strategy, paginationCriteria.pageRequest());
        return SlicedResponse.from(page);
    }


}
