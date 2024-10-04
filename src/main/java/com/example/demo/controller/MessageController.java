package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;

import com.example.demo.service.GroupService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/{groupId}")
    public List<Message> getMessagesByGroupId(@PathVariable String groupId) {
        System.out.println("groupId: " + groupId);

        return groupService.getMessagesByGroupId(groupId);
    }

    @GetMapping("/")
    public List<Message> getMessagesByGroupId2(@RequestParam String groupId) {
        System.out.println("groupId: " + groupId);
        return messageService.getMessagesByGroupId(groupId);

    }

}
