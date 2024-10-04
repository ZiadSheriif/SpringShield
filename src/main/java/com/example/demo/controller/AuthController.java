package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "User with this email already exists!";
        }

        // Hash the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Save the new user
        userRepository.save(user);
        return "User signed up successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println("====================================");
        System.out.println("User: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());

        System.out.println("====================================");
        // Check if the email exists
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            return "User does not exist!";
        }

        // Validate password
        boolean isPasswordMatch = passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
        if (!isPasswordMatch) {
            return "Invalid credentials!";
        }

        return "Login successful!";
    }
}
