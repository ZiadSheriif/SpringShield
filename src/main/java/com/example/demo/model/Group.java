package com.example.demo.model;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Column(nullable = false)
    private String title;
    
    @Column
    private String description;
    
    @Column
    private String avatar;
    
    
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;
    
    
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;
    
    public Group() {
    }
    
    public Group(String title, String description, String avatar) {
        this.title = title;
        this.description = description;
        this.avatar = avatar;
    }
    
    
    public Long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
    
}
