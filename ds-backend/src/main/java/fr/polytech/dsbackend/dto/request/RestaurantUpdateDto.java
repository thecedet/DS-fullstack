package fr.polytech.dsbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantUpdateDto {

    @JsonProperty(value = "nom", required = false)
    @Size(max = 90, message = "Le nom ne doit pas dépasser 90 caractères")
    private String nom;

    @JsonProperty(value = "addresse", required = false)
    @Size(max = 255, message = "L'addresse ne doit pas dépasser 255 caractères")
    private String addresse;

}
