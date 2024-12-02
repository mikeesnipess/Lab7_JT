package com.example.lab7_jt.service;

import com.example.lab7_jt.entities.Evaluation;
import com.example.lab7_jt.repository.EvaluationRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class EvaluationService {

    @Inject
    private EvaluationRepository evaluationRepository;

    public void submitEvaluation(Evaluation evaluation) {
        evaluationRepository.save(evaluation);
    }

    public List<Evaluation> getEvaluationsForTeacher(Long teacherId) {
        return evaluationRepository.findByTeacherId(teacherId);
    }
}
