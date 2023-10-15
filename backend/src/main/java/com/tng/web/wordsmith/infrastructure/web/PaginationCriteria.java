package com.tng.web.wordsmith.infrastructure.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@Data
public final class PaginationCriteria {
    @PositiveOrZero
    private int pageNumber;

    @Min(value = 5, message = "Minimum page size is 5")
    private int pageSize = 10;

    private String sortBy;
    private String sortOrder;


    public PageRequest pageRequest() {
        var sort = Sort.unsorted();
        if (StringUtils.hasText(getSortBy()) && StringUtils.hasText(getSortOrder())) {
            sort = Sort.by(Sort.Direction.fromString(getSortOrder()), getSortBy());
        } else if (StringUtils.hasText(getSortBy())) {
            sort = Sort.by(getSortBy());
        }
        return PageRequest.of(getPageNumber() > 0 ? getPageNumber() - 1 : 0,
                getPageSize(),
                sort);
    }
}
