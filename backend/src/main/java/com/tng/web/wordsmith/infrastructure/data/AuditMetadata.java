package com.tng.web.wordsmith.infrastructure.data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Embeddable
public final class AuditMetadata {
    @CreatedDate
    private Instant created;

    @LastModifiedDate
    private Instant lastModified;
}
