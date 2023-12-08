package fr.polytech.dsbackend.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationUpdateDto {

    @JsonProperty(value = "nom", required = false)
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    private String nom;

    @JsonProperty(value = "commentaire", required = false)
    @Size(max = 255, message = "Le commentaire ne doit pas dépasser 255 caractères")
    private String commentaire;

    @JsonProperty(value = "note")
    @Min(0)
    @Max(3)
    private Integer note;

    @JsonProperty(value = "date", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}
