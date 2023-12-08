package fr.polytech.dsbackend.helper;

import java.util.List;

import fr.polytech.dsbackend.entity.EvaluationEntity;

public class CalculMoyenneEval {
    
    public static float getMoyenne(List<EvaluationEntity> evaluations) {
        final Integer nbrEvaluation = evaluations.size();
        if(nbrEvaluation == 0) return -1;
        return evaluations.stream()
            .map(evaluation -> evaluation.getNote())
            .reduce(0, (notes, note) -> notes + note)
            /nbrEvaluation;
    }

}
