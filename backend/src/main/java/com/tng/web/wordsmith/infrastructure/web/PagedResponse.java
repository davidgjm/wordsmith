package com.tng.web.wordsmith.infrastructure.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
public final class PagedResponse<C> extends ApiResponse<List<C>> {
    private Pagination pagination;

    public PagedResponse(Page<C> data) {
        super(data.getContent());
        if (data.hasContent()) {
            this.pagination = Pagination.from(data);
        }
    }


}
