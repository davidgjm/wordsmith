package com.tng.web.wordsmith.infrastructure.data.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter(autoApply = true)
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return !CollectionUtils.isEmpty(stringList) ? String.join(DELIMITER, stringList) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return StringUtils.hasText(string) ? Arrays.asList(string.split(DELIMITER)) : new ArrayList<>();
    }
}
