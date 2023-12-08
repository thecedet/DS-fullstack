package fr.polytech.dsbackend.dto.response;

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
public class RestaurantSummaryDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("addresse")
    private String addresse;

    @JsonProperty("note_moyenne")
    private float noteMoyenne;

    @JsonProperty("etoiles")
    private EtoileDto etoiles;

    public static RestaurantSummaryDto convertEntitytoDto(RestaurantEntity entity) {
        return RestaurantSummaryDto.builder()
            .id(entity.getId())
            .nom(entity.getNom())
            .addresse(entity.getAddresse())
            .etoiles(entity.getEvaluationFinaleEntity() != null ? EtoileDto.convertEntitytoDto(entity.getEvaluationFinaleEntity()) : null)
            .noteMoyenne(CalculMoyenneEval.getMoyenne(entity.getEvaluations()))
            .build();
    }

}
