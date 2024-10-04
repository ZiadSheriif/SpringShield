package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

import java.util.HashMap;
import java.util.List;
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
    private JwtUtil jwtUtil;

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

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!user.getEmail().matches(emailRegex)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("message", "Invalid email address!");
                }
            });
        }

        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (!user.getPassword().matches(passwordRegex)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("message",
                            "Password must contain at least 8 characters, one uppercase, one lowercase, one number and one special character!");
                }
            });
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.saveUser(user);
        return ResponseEntity.ok().body(new HashMap<String, String>() {
            {
                put("message", "User registered successfully!");
            }
        });

        // return ResponseEntity.ok("User registered successfully!"); in case of a
        // String response
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {

        Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
        if (!existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<String, String>() {
                {
                    put("message", "User not found!");
                }
            });

        }

        // Validate password
        boolean isPasswordMatch = passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword());
        if (!isPasswordMatch) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<String, String>() {
                {
                    put("message", "Invalid credentials!");
                }
            });
        }

        User loggedUser = existingUser.get();
        String token = jwtUtil.generateToken(loggedUser.getUsername());

        return ResponseEntity.ok().body(new HashMap<String, String>() {
            {
                put("message", "User logged in successfully!");
                put("token", token);
            }
        });
    }
}
