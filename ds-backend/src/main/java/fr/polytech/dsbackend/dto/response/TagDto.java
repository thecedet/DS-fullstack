package fr.polytech.dsbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.polytech.dsbackend.entity.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    
    @JsonProperty("nom")
    private String nom;

    public static TagDto convertEntitytoDto(TagEntity entity) {
        return TagDto.builder().nom(entity.name()).build();
    }

}
