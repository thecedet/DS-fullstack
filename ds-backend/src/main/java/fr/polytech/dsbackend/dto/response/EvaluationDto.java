package fr.polytech.dsbackend.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.polytech.dsbackend.entity.EvaluationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDto {

   @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("note")
    private Integer note;

    @JsonProperty("date_creation")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateCreation;

    @JsonProperty("date_mise_a_jour")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateMiseAJour;

    public static EvaluationDto convertEntitytoDto(EvaluationEntity entity) {
        return EvaluationDto.builder()
            .id(entity.getId())
            .nom(entity.getNom())
            .note(entity.getNote())
            .dateCreation(entity.getDateCreation())
            .dateMiseAJour(entity.getDateMiseAJour())
            .build();
    } 
}
