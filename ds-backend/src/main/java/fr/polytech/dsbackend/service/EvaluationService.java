package fr.polytech.dsbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.polytech.dsbackend.dto.request.EvaluationUpdateDto;
import fr.polytech.dsbackend.dto.request.EvaluationCreateDto;
import fr.polytech.dsbackend.entity.EvaluationEntity;
import fr.polytech.dsbackend.exception.ResourceNotFoundException;
import fr.polytech.dsbackend.repository.EvaluationRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final RestaurantService restaurantService;

    public List<EvaluationEntity> getEvaluations() {
        return this.evaluationRepository.findAll();
    }

    public EvaluationEntity getEvaluation(Integer id) {
        return this.evaluationRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("L'id est introuvable")
        );
    }

    public EvaluationEntity addEvaluation(Integer id, EvaluationCreateDto evaluation) {
        final EvaluationEntity entity = EvaluationEntity.builder()
            .nom(evaluation.getNom())
            .commentaire(evaluation.getCommentaire())
            .note(evaluation.getNote())
            .dateCreation(evaluation.getDate())
            .restaurant(this.restaurantService.getRestaurant(id))
            .build();

        this.evaluationRepository.save(entity);
        
        return entity;
    }

    public EvaluationEntity editEvaluation(Integer id, EvaluationUpdateDto evaluation) {
        final EvaluationEntity entity = this.getEvaluation(id);

        if(evaluation.getNom() != null) entity.setNom(evaluation.getNom());
        if(evaluation.getCommentaire() != null) entity.setCommentaire(evaluation.getCommentaire());
        if(evaluation.getNote() != null) entity.setNote(evaluation.getNote());
        if(evaluation.getDate() != null) entity.setDateMiseAJour(evaluation.getDate());

        this.evaluationRepository.save(entity);
        return entity;
    }

    public String deleteEvaluation(Integer id) {
        this.getEvaluation(id);
        this.evaluationRepository.deleteById(id);
        return "L'evaluation vient d'être supprimée";
    }

}
