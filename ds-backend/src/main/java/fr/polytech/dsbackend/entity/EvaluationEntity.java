package fr.polytech.dsbackend.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity(name = "evaluations")
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationEntity {
    
    @Id
    @GeneratedValue()
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Column(
        name = "nom",
        columnDefinition = "varchar(50)",
        nullable=false
    )
    private String nom;

    @Column(
        name = "commentaire",
        columnDefinition = "varchar(255)",
        nullable=false
    )
    private String commentaire;

    @Column(
        name = "note",
        columnDefinition = "int",
        nullable = false
    )
    private Integer note;

    @Column(
        name = "dateCreation",
        columnDefinition = "date",
        nullable =  false
    )
    private LocalDate dateCreation;

    @Column(
        name = "dateMiseAJour",
        columnDefinition = "date",
        nullable =  true
    )
    private LocalDate dateMiseAJour;

    // mettre les photos

}
