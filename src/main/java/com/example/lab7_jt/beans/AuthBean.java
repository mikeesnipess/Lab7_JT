package com.example.lab7_jt.beans;

import com.example.lab7_jt.entities.User;
import com.example.lab7_jt.entities.UserRole;
import com.example.lab7_jt.service.UserService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Named
@SessionScoped
public class AuthBean implements Serializable {
    private String username;
    private String password;
    private String role; // Role (e.g., Admin, Student, Teacher)
    private User loggedInUser;

    @Inject
    private UserService userService;

    // Login method
    public String login() {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // Optionally add message: Invalid credentials
            return "login.xhtml"; // Stay on login page if credentials are empty
        }

        User user = userService.findUserByUsername(username);
        UUID userId = user.getId();
        String roleUser = userService.getUserRoles(userId).getFirst();

        if (user != null && user.getPassword().equals(password) && roleUser.equals("Student")) {
            loggedInUser = user; // Set logged-in user
            return "studentEvaluation.xhtml"; // Redirect to dashboard
        }else if(user != null && user.getPassword().equals(password) && roleUser.equals("Teacher")) {
            loggedInUser = user;
            TeacherEvaluationBean teacherEvaluationBean = FacesContext.getCurrentInstance()
                    .getApplication()
                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{teacherEvaluationBean}", TeacherEvaluationBean.class);
            teacherEvaluationBean.setLoggedInTeacher(loggedInUser);
            teacherEvaluationBean.loadEvaluations(); // Load evaluations
            return "teacherEvaluation.xhtml";
        }
        else if (user != null && user.getPassword().equals(password) && roleUser.equals("Admin")) {
            loggedInUser = user;
            AdminEvaluationBean adminEvaluationBean = FacesContext.getCurrentInstance()
                    .getApplication()
                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{adminEvaluationBean}", AdminEvaluationBean.class);
            adminEvaluationBean.loadAllEvaluations();
            return "adminEvaluation.xhtml";
        }

        else {
            // Optionally, add a message for incorrect login
            return "login.xhtml"; // Redirect to login page
        }
    }

    // Register method
    public String register() {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || role == null || role.isEmpty()) {
            // Optionally add a message: Missing credentials or role
            return "register.xhtml"; // Stay on registration page
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        // Create and assign the role
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(newUser);

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole); // Add the selected role
        newUser.setRoles(roles);

        try {
            userService.registerUser(newUser); // Save user to the database
            loggedInUser = newUser; // Set the newly registered user as the logged-in user
            return "login.xhtml"; // Redirect to the login page
        } catch (Exception e) {
            // Optionally, add a message for registration failure
            return "register.xhtml"; // Stay on registration page
        }
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String   getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
