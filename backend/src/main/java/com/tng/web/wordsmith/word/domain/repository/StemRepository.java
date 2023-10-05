package com.tng.web.wordsmith.word.domain.repository;

import com.tng.web.wordsmith.infrastructure.data.BaseRepository;
import com.tng.web.wordsmith.word.domain.model.Stem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StemRepository extends BaseRepository<Stem> {

    Optional<Stem> findByTermIgnoreCase(String stem);

    @Query("select s.id from Stem s order by s.id")
    List<Long> findAllIds();

    Page<Stem> findByIdInOrderByCreated(List<Long> stemIds, Pageable pageRequest);
}
