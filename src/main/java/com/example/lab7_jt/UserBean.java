//package com.example.lab7_jt;
//
//import com.example.lab7_jt.entities.User;
//import com.example.lab7_jt.service.UserService;
//import jakarta.enterprise.context.RequestScoped;
//import jakarta.inject.Inject;
//import jakarta.inject.Named;
//
//@Named
//@RequestScoped
//public class UserBean {
//    private User user = new User();
//    private String role;
//
//    // Inject UserService instead of UserRepository
//    @Inject
//    private UserService userService;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    // Update register method to use UserService
//    public String register() {
//        try {
//            System.out.println("Register method called");  // Add logging
//            user.setRole(role); // Set the role
//            userService.registerUser(user); // Use UserService to save the user
//            return "success.xhtml?faces-redirect=true"; // Navigate to a success page
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null; // Stay on the same page on failure
//        }
//    }
//
//}
