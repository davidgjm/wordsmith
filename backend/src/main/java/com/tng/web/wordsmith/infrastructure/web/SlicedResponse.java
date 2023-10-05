package com.tng.web.wordsmith.infrastructure.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
public final class SlicedResponse<C> extends ApiResponse<List<C>> {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Pagination pagination;

    public SlicedResponse(Page<C> data) {
        super(data.getContent());
        if (data.hasContent()) {
            this.pagination = Pagination.from(data);
        }
    }

    public SlicedResponse(List<C> items) {
        super(items);
    }

    public static <T> SlicedResponse<T> from(Page<T> page) {
        if (page == null) {
            return new SlicedResponse<>(Collections.emptyList());
        }
        return new SlicedResponse<>(page);
    }

    public static <T> SlicedResponse<T> from(List<T> data) {
        return new SlicedResponse<>(data != null ? data : Collections.emptyList());
    }
}
