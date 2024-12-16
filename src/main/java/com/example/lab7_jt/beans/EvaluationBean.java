package com.example.lab7_jt.beans;

import com.example.lab7_jt.entities.Evaluation;
import com.example.lab7_jt.entities.User;
import com.example.lab7_jt.service.EvaluationService;
import com.example.lab7_jt.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Named
@SessionScoped
public class EvaluationBean  implements Serializable {
    private String activity;
    private String activityType;
    private Integer grade;
    private String comment;
    private User teacher; // To hold the selected teacher
    private List<User> teachers;  // List of teachers for the dropdown
    private String successMessage;
    private UUID teacherId;

    @Inject
    private EvaluationService evaluationService;

    @Inject
    private UserService userService;

    @Inject
    private AuthBean authBean;


    @PostConstruct
    public void init() {
        // Load the list of teachers when the bean is initialized
        this.teachers = userService.getAllTeachers();
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String submitEvaluation() {
        Evaluation evaluation = new Evaluation();
        evaluation.setActivity(activity);
        evaluation.setActivityType(activityType);
        evaluation.setGrade(grade);
        evaluation.setComment(comment);
        evaluation.setTeacher(teacher);
        evaluation.setStudent(authBean.getLoggedInUser());

        evaluationService.submitEvaluation(evaluation);  // Save to DB

        successMessage = "Evaluation submitted successfully!";
        return null;  // Stay on the same page
    }

    public int getRemainingTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime windowEnd = evaluationService.getWindowEndTime();
        return (int) Duration.between(now, windowEnd).toSeconds();
    }

    // Getters and setters for successMessage
    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public List<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<User> teachers) {
        this.teachers = teachers;
    }

    // Getters and setters for all properties

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public EvaluationService getEvaluationService() {
        return evaluationService;
    }

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }
}
