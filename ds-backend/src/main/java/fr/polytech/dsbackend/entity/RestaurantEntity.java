package fr.polytech.dsbackend.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity(name = "restaurants")
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    
    @Id
    @GeneratedValue()
    private Integer id;

    @Column(
        name = "nom",
        columnDefinition = "varchar(90)",
        nullable = false
    )
    private String nom;

    @Column(
        name = "addresse",
        columnDefinition = "varchar(255)",
        nullable = false
    )
    private String addresse;

    @OneToMany(
        mappedBy = "restaurant",
        cascade = CascadeType.ALL
    )
    private List<EvaluationEntity> evaluations;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(
        name = "tags",
        nullable = false
    )
    private List<TagEntity> tags;

    @Column(
        name = "image",
        columnDefinition = "boolean",
        nullable = false
    )
    private Boolean image;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluationFinale_id", referencedColumnName = "id", nullable = true)
    private EvaluationFinaleEntity evaluationFinaleEntity;
     

}
