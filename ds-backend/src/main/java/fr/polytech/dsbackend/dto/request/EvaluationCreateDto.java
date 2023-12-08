package fr.polytech.dsbackend.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class EvaluationCreateDto {

    @JsonProperty("nom")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    @NotNull(message = "Paramètre manquant : nom")
    @NotBlank(message = "Paramètre manquant : nom")
    private String nom;

    @JsonProperty("commentaire")
    @Size(max = 255, message = "Le commentaire ne doit pas dépasser 255 caractères")
    @NotNull(message = "Paramètre manquant : commentaire")
    @NotBlank(message = "Paramètre manquant : commentaire")
    private String commentaire;

    @JsonProperty("note")
    @Min(0)
    @Max(3)
    @NotNull(message = "Paramètre manquant : note")
    private Integer note;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Paramètre manquant : date")
    private LocalDate date;

}
