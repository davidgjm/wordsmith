package com.tng.web.wordsmith.infrastructure.config;

import org.springframework.modulith.core.ApplicationModuleDetectionStrategy;
import org.springframework.modulith.core.JavaPackage;

import java.util.stream.Stream;

public class CustomApplicationModuleDetectionStrategy implements ApplicationModuleDetectionStrategy {
    @Override
    public Stream<JavaPackage> getModuleBasePackages(JavaPackage basePackage) {
        return basePackage.getDirectSubPackages().stream()
                .filter(p -> ! p.getLocalName().matches("infrastructure"))
                ;
    }
}
