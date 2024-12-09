package com.example.lab7_jt.beans;

import com.example.lab7_jt.entities.Evaluation;
import com.example.lab7_jt.service.EvaluationService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AdminEvaluationBean implements Serializable {

    @Inject
    private EvaluationService evaluationService;

    private List<Evaluation> allEvaluations;

    public void loadAllEvaluations() {
        allEvaluations = evaluationService.getAllEvaluations();
    }

    // Getters and Setters
    public List<Evaluation> getAllEvaluations() {
        return allEvaluations;
    }
}
