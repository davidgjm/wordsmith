package com.tng.web.wordsmith.infrastructure.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@RequiredArgsConstructor
@Data
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Integer stemSelectionSize = 10;
}
