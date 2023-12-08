package fr.polytech.dsbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity(name = "evaluations_finale")
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationFinaleEntity {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(
        name = "auteur",
        columnDefinition = "varchar(90)",
        nullable=false
    )
    private String auteur;

    @Column(
        name = "note",
        columnDefinition = "int",
        nullable = false
    )
    private Integer note;

    @Column(
        name = "description",
        columnDefinition = "text",
        nullable = false
    )
    private String description;

    @OneToOne(mappedBy = "evaluationFinaleEntity")
    private RestaurantEntity restaurant;
    
}
