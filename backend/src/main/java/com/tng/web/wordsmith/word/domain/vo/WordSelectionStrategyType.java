package com.tng.web.wordsmith.word.domain.vo;

import org.springframework.util.Assert;

import java.util.stream.Stream;

public enum WordSelectionStrategyType {
    DEFAULT,
    RANDOM,
    EARLIEST,
    MOST_FAILED_ATTEMPTS
    ;

    public static WordSelectionStrategyType resolve(String strategy) {
        Assert.hasText(strategy, "Provided strategy value cannot be empty!");
        return Stream.of(values())
                .filter(s -> s.name().equalsIgnoreCase(strategy.trim()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
