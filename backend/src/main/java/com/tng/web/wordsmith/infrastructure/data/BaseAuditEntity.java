package com.tng.web.wordsmith.infrastructure.data;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
public abstract class BaseAuditEntity extends BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private Instant created;

    @LastModifiedDate
    private Instant lastModified;
}
