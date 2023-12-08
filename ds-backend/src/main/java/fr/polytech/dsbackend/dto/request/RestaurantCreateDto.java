package fr.polytech.dsbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCreateDto {

    @JsonProperty("nom")
    @Size(max = 90, message = "Le nom ne doit pas dépasser 90 caractères")
    @NotNull(message = "Paramètre manquant : nom")
    @NotBlank(message = "Paramètre manquant : nom")
    private String nom;

    @JsonProperty("addresse")
    @Size(max = 255, message = "L'addresse ne doit pas dépasser 255 caractères")
    @NotNull(message = "Paramètre manquant : addresse")
    @NotBlank(message = "Paramètre manquant : addresse")
    private String addresse;

}
