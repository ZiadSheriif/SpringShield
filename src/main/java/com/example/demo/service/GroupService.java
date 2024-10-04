package com.example.demo.service;

import com.example.demo.model.Group;
import com.example.demo.model.Message;
import com.example.demo.repository.GroupRepository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupService() {
    }

    public void deleteGroup(ObjectId id) {
        groupRepository.deleteById(id);
    }

    public long countGroups() {
        return groupRepository.count();
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroupById(ObjectId id) {
        return groupRepository.findById(id).orElse(null);
    }

    public boolean existsGroup(ObjectId id) {
        return groupRepository.existsById(id);
    }

    public List<Message> getMessagesByGroupId(ObjectId groupId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        System.out.println("group: " + group.getMessages());
        return group.getMessages();
    }

}
