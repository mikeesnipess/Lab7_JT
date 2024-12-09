package com.example.lab7_jt.beans;

import com.example.lab7_jt.entities.Evaluation;
import com.example.lab7_jt.entities.User;
import com.example.lab7_jt.service.EvaluationService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TeacherEvaluationBean implements Serializable {

    @Inject
    private EvaluationService evaluationService; // Inject the service

    private List<Evaluation> teacherEvaluations; // To store evaluations
    private User loggedInTeacher; // The logged-in teacher

    public void loadEvaluations() {
        if (loggedInTeacher != null) {
            teacherEvaluations = evaluationService.getEvaluationsByTeacherId(loggedInTeacher.getId());
        }
    }

    // Getters and Setters
    public List<Evaluation> getTeacherEvaluations() {
        return teacherEvaluations;
    }

    public void setLoggedInTeacher(User loggedInTeacher) {
        this.loggedInTeacher = loggedInTeacher;
    }
    public User getLoggedInTeacher() {
        return loggedInTeacher;
    }

}

