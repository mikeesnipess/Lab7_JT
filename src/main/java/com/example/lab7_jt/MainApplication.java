//package com.example.lab7_jt;
//
//import com.example.lab7_jt.entities.Evaluation;
//import com.example.lab7_jt.entities.User;
//import com.example.lab7_jt.service.UserService;
//import com.example.lab7_jt.service.EvaluationService;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//
//public class MainApplication {
//    public static void main(String[] args) {
//        // Create EntityManagerFactory and EntityManager manually
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
//        EntityManager em = emf.createEntityManager();
//
//        // Manually create the services and inject the EntityManager
//        UserService userService = new UserService();
//        EvaluationService evaluationService = new EvaluationService(em);
//
//        // Register a new user
//        User user = new User();
//        user.setUsername("john_doe");
//        user.setPassword("password123");
//        user.setRoles(new HashSet<>());
//        user.getRoles().add("STUDENT");
//        userService.registerUser(user);
//
//        // Find the user by username
//        User foundUser = userService.findUserByUsername("john_doe");
//        System.out.println("Found user: " + foundUser.getUsername());
//
//        // Create an evaluation for the user
//        Evaluation evaluation = new Evaluation();
//        evaluation.setTeacher(foundUser);
//        evaluation.setStudent(foundUser);
//        evaluation.setActivity("Mathematics Lecture");
//        evaluation.setActivityType("course");
//        evaluation.setGrade(9);
//        evaluation.setComment("Good lecture");
//        evaluation.setRegistrationNumber("12345");
//        evaluation.setTimestamp(LocalDateTime.now());
//
//        // Submit the evaluation
//        evaluationService.submitEvaluation(evaluation);
//
//        // Close the EntityManager and EntityManagerFactory
//        em.close();
//        emf.close();
//    }
//}
