package com.example.demo.service;

import com.example.demo.model.Group;
import com.example.demo.model.Message;
import com.example.demo.repository.GroupRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupService() {
    }

    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }

    public long countGroups() {
        return groupRepository.count();
    }

    public List<Message> getMessagesByGroupId(String groupId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        System.out.println("group: " + group.getMessages());
        return group.getMessages();
    }

}
