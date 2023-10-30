package com.tng.web.wordsmith.infrastructure.data.converters;

import com.tng.web.wordsmith.word.domain.vo.PartOfSpeech;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

@Converter(autoApply = true)
public class PartOfSpeechConverter implements AttributeConverter<PartOfSpeech, String> {
    @Override
    public String convertToDatabaseColumn(PartOfSpeech attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public PartOfSpeech convertToEntityAttribute(String code) {
        if (!StringUtils.hasText(code)) {
            return null;
        }

        return PartOfSpeech.resolve(code);
    }
}
