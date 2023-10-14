package com.tng.web.wordsmith.word.domain.model;

import com.tng.web.wordsmith.infrastructure.data.BaseAuditEntity;
import com.tng.web.wordsmith.infrastructure.data.PartOfSpeech;
import com.tng.web.wordsmith.infrastructure.data.converters.StringListConverter;
import com.tng.web.wordsmith.word.CreateWordRequest;
import com.tng.web.wordsmith.word.UpdateWordRequest;
import com.tng.web.wordsmith.word.WordDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_stem_pos", columnNames = {"stem_id", "partOfSpeech"})
        }
)
@EntityListeners(AuditingEntityListener.class)
public class Word extends BaseAuditEntity {

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Stem stem;

    @NotNull
    @Column(nullable = false)
    private PartOfSpeech partOfSpeech;

    @Column(length = 64)
    private String ipa;

    @Convert(converter = StringListConverter.class)
    @Column(length = 255)
    private List<String> variants = new ArrayList<>();

    @NotBlank
    @Column(nullable = false)
    private String translation;

    @Column(length = 1024)
    private String explanation;

    @Column(length = 1024)
    private String example;

    public final void partOfSpeech(String code) {
        Assert.hasText(code, "Part of speech code cannot be empty!");
        this.partOfSpeech = PartOfSpeech.resolve(code);
    }

    public final void addVariant(@NotBlank String variant) {
        this.variants.add(variant);
    }

    public final void addVariants(List<String> values) {
        if (!CollectionUtils.isEmpty(values)) {
            this.variants.addAll(values);
        }
    }

    /**
     * Update entity properties with information included in the provided DTO.
     *
     * @param dto
     */
    public void updateFields(WordDto dto) {
        if (dto == null) {
            return;
        }
        partOfSpeech(dto.getPartOfSpeech());
        setIpa(dto.getIpa());
        setTranslation(dto.getTranslation());
        setExplanation(dto.getExplanation());
        setExample(dto.getExample());
    }

    public void updateFields(CreateWordRequest request) {
        if (request == null) {
            return;
        }
        partOfSpeech(request.getPartOfSpeech());
        setIpa(request.getIpa());
        setTranslation(request.getTranslation());
        setExplanation(request.getExplanation());
        setExample(request.getExample());
        this.addVariants(request.getVariants());
    }

    public void updateFields(UpdateWordRequest request) {
        if (request == null) {
            return;
        }
        partOfSpeech(request.getPartOfSpeech());
        setIpa(request.getIpa());
        setTranslation(request.getTranslation());
        setExplanation(request.getExplanation());
        setExample(request.getExample());
        this.addVariants(request.getVariants());
    }


}
