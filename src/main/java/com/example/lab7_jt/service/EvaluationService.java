package com.example.lab7_jt.service;

import com.example.lab7_jt.entities.Evaluation;
import com.example.lab7_jt.entities.User;
import com.example.lab7_jt.repository.EntityManagerProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class EvaluationService {

    private EntityManager em;

    @Inject
    public void setEvaluationService(EntityManagerProducer emp) {
        this.em = emp.getEntityManager();
    }

    public EvaluationService() {}


    public void submitEvaluation(Evaluation evaluation) {
        LocalDateTime now = LocalDateTime.now();
        evaluation.setTimestamp(now);  // Set timestamp to current time
        evaluation.setRegistrationNumber(generateRegistrationNumber());  // Generate a registration number

        // Begin transaction and save to the database
        em.getTransaction().begin();
        em.persist(evaluation);
        em.getTransaction().commit();
    }



    public List<Evaluation> getEvaluationsByTeacher(User teacher) {
        return em.createQuery("SELECT e FROM Evaluation e WHERE e.teacher = :teacher", Evaluation.class)
                .setParameter("teacher", teacher)
                .getResultList();
    }

    public List<Evaluation> getAllEvaluations() {
        return em.createQuery("SELECT e FROM Evaluation e", Evaluation.class).getResultList();
    }

    public LocalDateTime getWindowEndTime() {
        // Example logic: End time is 10 minutes from now
        return LocalDateTime.now().plusMinutes(10);
    }

    private String generateRegistrationNumber() {
        return "REG" + System.currentTimeMillis(); // Simple unique registration number generator
    }

    public List<Evaluation> getEvaluationsByTeacherId(UUID teacherId) {
        return em.createQuery(
                        "SELECT e FROM Evaluation e WHERE e.teacher.id = :teacherId", Evaluation.class)
                .setParameter("teacherId", teacherId)
                .getResultList();
    }

}
