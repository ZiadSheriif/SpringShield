package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.GroupService;
import com.example.demo.service.MessageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/{groupId}")
    public ResponseEntity<List<Message>> getMessagesByGroupId(@PathVariable ObjectId groupId) {
        System.out.println("groupId: " + groupId);
        List<Message> messages = groupService.getMessagesByGroupId(groupId);

        if (messages.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no messages found
        }
        return ResponseEntity.ok(messages); // Return 200 with the list of messages
    }

    @GetMapping("/")
    public ResponseEntity<List<Message>> getMessagesByGroupIdQuery(@RequestParam ObjectId groupId) {
        System.out.println("groupId: " + groupId);
        List<Message> messages = messageService.getMessagesByGroupId(groupId);

        if (messages.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no messages found
        }
        return ResponseEntity.ok(messages); // Return 200 with the list of messages
    }
}
