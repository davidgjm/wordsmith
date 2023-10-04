package com.tng.web.wordsmith.word.domain.model;

import com.tng.web.wordsmith.infrastructure.data.BaseEntity;
import com.tng.web.wordsmith.infrastructure.data.PartOfSpeech;
import com.tng.web.wordsmith.infrastructure.data.converters.StringListConverter;
import com.tng.web.wordsmith.word.WordDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;

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
public class Word extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Stem stem;

    @NotNull
    @Column(nullable = false)
    private PartOfSpeech partOfSpeech;

    @Convert(converter = StringListConverter.class)
    @Column(length = 255)
    private List<String> variants = new ArrayList<>();

    @NotBlank
    @Column(nullable = false)
    private String meaning;

    @Column(length = 512)
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


    /**
     * Update entity properties with information included in the provided DTO.
     * @param dto
     */
    public void updateProperties(WordDto dto) {
        if (dto == null) {
            return;
        }
        partOfSpeech(dto.getPartOfSpeech());
        this.meaning = dto.getMeaning();
        this.explanation = dto.getExplanation();
        this.example = dto.getExample();
    }
}
