package com.tng.web.wordsmith.word.domain.repository;

import com.tng.web.wordsmith.infrastructure.data.BaseRepository;
import com.tng.web.wordsmith.word.domain.model.TermRelation;

import java.util.List;

public interface TermRelationRepository extends BaseRepository<TermRelation> {
    List<TermRelation> findBySourceId(Long sourceId);

}
