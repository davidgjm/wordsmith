package com.tng.web.wordsmith.infrastructure.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ApiResponse<T> {
    private final T data;

    public static <T> ApiResponse<T> from(T data) {
        return new ApiResponse<>(data);
    }
}
