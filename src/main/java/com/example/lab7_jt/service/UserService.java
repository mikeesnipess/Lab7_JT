package com.example.lab7_jt.service;

import com.example.lab7_jt.entities.User;
import com.example.lab7_jt.repository.UserRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid credentials");
    }
}
