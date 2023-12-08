package fr.polytech.dsbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.polytech.dsbackend.entity.EvaluationFinaleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EtoileDto {
    
    @JsonProperty("auteur")
    private String auteur;

    @JsonProperty("note")
    private Integer note;

    @JsonProperty("description")
    private String description;

    public static EtoileDto convertEntitytoDto(EvaluationFinaleEntity entity) {
        return EtoileDto.builder()
            .auteur(entity.getAuteur())
            .note(entity.getNote())
            .description(entity.getDescription())
            .build();
    }

}
