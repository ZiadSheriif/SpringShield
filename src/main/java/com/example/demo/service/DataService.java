package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    private static List<User> users = new ArrayList<>();
    private static List<Message> messages = new ArrayList<>();

    // Static block to initialize some data
    static {
        users.add(new User("Alice", "alice@example.com", "password123"));
        users.add(new User("Bob", "bob@example.com", "password456"));

        messages.add(new Message("Hello, this is Alice!"));
        messages.add(new Message("Hi Alice, this is Bob!"));
    }

    // Simulated database operations
    public List<User> getAllUsers() {
        return users;
    }

    public List<Message> getAllMessages() {
        return messages;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
