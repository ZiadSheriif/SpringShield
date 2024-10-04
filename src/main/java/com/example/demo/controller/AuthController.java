package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody User user) {
        user.printUserInfo();

        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "User already exists!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Save the new user
        // userService.saveUser(user);
        return ResponseEntity.ok().body(new HashMap<String, String>() {
            {
                put("message", "User registered successfully!");
            }
        });

        // return ResponseEntity.ok("User registered successfully!"); in case of a
        // String response
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        user.printUserInfo();

        Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
        if (!existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found!");

        }

        // Validate password
        boolean isPasswordMatch = passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword());
        if (!isPasswordMatch) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password!");
        }

        return ResponseEntity.ok("User logged in successfully!");
    }
}
