//User.java

package com.journal.learn.Journal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")  // Removed @Repository - entities shouldn't have this annotation
public class User {
    @Id
    private String userId;

    private String username;

    private String password;
    @DBRef
    private List<JournalEntry> JournalEntries =new ArrayList<>();
    private List<String>roles;
    // Default constructor (required by MongoDB)
    public User() {}

    // Getters and Setters


    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<JournalEntry> getJournalEntries() {
        return JournalEntries;
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        JournalEntries = journalEntries;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}