package fr.polytech.dsbackend.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.polytech.dsbackend.entity.RestaurantEntity;
import fr.polytech.dsbackend.helper.CalculMoyenneEval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("addresse")
    private String addresse;

    @JsonProperty("note_moyenne")
    private float noteMoyenne;

    @JsonProperty("evaluations")
    private List<EvaluationDto> evaluations;

    @JsonProperty("illustration")
    private String illustration;

    @JsonProperty("tag")
    private List<TagDto> tags;

    @JsonProperty("etoiles")
    private EtoileDto etoiles;

    public static RestaurantDto convertEntitytoDto(RestaurantEntity entity) {
        return RestaurantDto.builder()
            .id(entity.getId())
            .nom(entity.getNom())
            .addresse(entity.getAddresse())
            .noteMoyenne(CalculMoyenneEval.getMoyenne(entity.getEvaluations()))
            .evaluations(entity.getEvaluations().stream()
                .map(evaluation -> EvaluationDto.convertEntitytoDto(evaluation)).toList()
            )
            .tags(entity.getTags().stream().map(tag -> TagDto.convertEntitytoDto(tag)).toList())
            .etoiles(entity.getEvaluationFinaleEntity() != null ? EtoileDto.convertEntitytoDto(entity.getEvaluationFinaleEntity()) : null)
            .build();
    }

}
