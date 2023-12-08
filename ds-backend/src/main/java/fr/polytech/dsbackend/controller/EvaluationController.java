package fr.polytech.dsbackend.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.polytech.dsbackend.dto.request.EvaluationCreateDto;
import fr.polytech.dsbackend.dto.request.EvaluationUpdateDto;
import fr.polytech.dsbackend.dto.response.MessageDto;
import fr.polytech.dsbackend.dto.response.EvaluationDto;
import fr.polytech.dsbackend.service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequiredArgsConstructor
public class EvaluationController {
    
    private final EvaluationService evaluationService;

    @GetMapping("/evaluations")
    public @ResponseBody List<EvaluationDto> getevaluations() {
        return this.evaluationService.getEvaluations().stream().map(
            evaluation -> EvaluationDto.convertEntitytoDto(evaluation)
        ).toList();
    }

    @GetMapping("/evaluations/{id}")
    public @ResponseBody EvaluationDto getEvaluation(@PathVariable Integer id) {
        return EvaluationDto.convertEntitytoDto(this.evaluationService.getEvaluation(id));
    }

    @PostMapping("/restaurants/{id}/evaluations")
    public @ResponseBody EvaluationDto addEvaluation(@PathVariable Integer id, @Valid @RequestBody EvaluationCreateDto evaluation) {
        return EvaluationDto.convertEntitytoDto(this.evaluationService.addEvaluation(id, evaluation));
    }

    @PutMapping("/evaluations/{id}")
    public @ResponseBody EvaluationDto editEvaluation(@PathVariable Integer id, @Valid @RequestBody EvaluationUpdateDto evaluation) {
        return EvaluationDto.convertEntitytoDto(this.evaluationService.editEvaluation(id, evaluation));
    }

    @DeleteMapping("/evaluations/{id}")
    public @ResponseBody MessageDto deleteEvaluation(@PathVariable Integer id) {
        return MessageDto.builder()
            .code("DELETE_OK")
            .message(this.evaluationService.deleteEvaluation(id))
            .build();
    }
    

}
