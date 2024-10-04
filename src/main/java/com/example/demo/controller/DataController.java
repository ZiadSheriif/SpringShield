package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.Message;
import com.example.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private DataService dataService;

    // Get all users
    @GetMapping("/users")
    public List<User> getUsers() {
        return dataService.getAllUsers();
    }

    // Add a new user
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        dataService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    // Get all messages
    @GetMapping("/messages")
    public List<Message> getMessages() {
        return dataService.getAllMessages();
    }

    // Add a new message
    @PostMapping("/messages")
    public ResponseEntity<String> createMessage(@RequestBody Message message) {
        dataService.addMessage(message);
        return ResponseEntity.ok("Message added successfully");
    }
}
