package fr.polytech.dsbackend.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.dsbackend.dto.response.TagDto;
import fr.polytech.dsbackend.entity.TagEntity;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TagController {
    
    @GetMapping("tags")
    public @ResponseBody List<TagDto> getTags() {
        return Stream.of(TagEntity.values())
            .map(tag -> TagDto.convertEntitytoDto(tag)).toList();
    }

}
