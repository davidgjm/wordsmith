package com.tng.web.wordsmith.word;

import com.tng.web.wordsmith.infrastructure.web.ApiResponse;
import com.tng.web.wordsmith.word.domain.vo.PartOfSpeech;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/metadata/v1")
@Validated
@RequiredArgsConstructor
public class MetadataController {
    @GetMapping("/parts-of-speech")
    public ApiResponse<List<PartOfSpeechDto>> getPartsOfSpeech() {
        List<PartOfSpeechDto> data = Stream.of(PartOfSpeech.values())
                .map(p -> new PartOfSpeechDto(p.getCode(), p.getDescription()))
                .sorted(Comparator.comparing(PartOfSpeechDto::description))
                .toList();
        return new ApiResponse<>(data);
    }
}
