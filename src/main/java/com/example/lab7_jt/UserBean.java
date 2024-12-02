package com.example.lab7_jt;

import com.example.lab7_jt.entities.User;
import com.example.lab7_jt.repository.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserBean {

    private User user = new User();
    private String role;

    @Inject
    private UserRepository userRepository;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String register() {
        try {
            // Set the role in the user entity
            user.setRole(role);

            // Save the user using the UserRepository
            userRepository.save(user);

            // Navigate to a success page or display a success message
            return "success.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions and show an error message
            return null;
        }
    }
}
