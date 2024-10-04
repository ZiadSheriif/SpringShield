package com.example.demo.controller;

import com.example.demo.model.Group;
import com.example.demo.service.GroupService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{groupId}")
    public ResponseEntity<List<Group>> getMessagesByGroupId(@PathVariable ObjectId groupId) {
        System.out.println("groupId: " + groupId);
        List<Group> groups = groupService.getAllGroups();
        
        if (groups.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no groups found
        }
        return ResponseEntity.ok(groups); // Return 200 with the list of groups
    }

}
