package fr.polytech.dsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.dsbackend.entity.EvaluationEntity;

public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Integer> {
    
}
