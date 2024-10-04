package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.MessageRepository;
import com.example.demo.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public MessageService() {
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(String id) {
        return messageRepository.findById(id).orElse(null);
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }

    public long countMessages() {
        return messageRepository.count();
    }

    public boolean existsMessage(String id) {
        return messageRepository.existsById(id);
    }

    public List<Message> getMessagesByGroupId(String groupId) {
        return messageRepository.findAll();
    }
}
