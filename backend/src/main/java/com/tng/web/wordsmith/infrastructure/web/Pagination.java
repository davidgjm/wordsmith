package com.tng.web.wordsmith.infrastructure.web;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Builder
@Data
public final class Pagination {
    private final int totalPages;
    private final long totalElements;
    private final int pageNumber;
    private final int pageSize;
    private boolean first;
    private boolean last;

    public static <T> Pagination from(Page<T> page) {
        return Pagination.builder()
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .pageNumber(page.getNumber()+1)
                .pageSize(page.getSize())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
