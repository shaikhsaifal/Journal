
//JournalEntry.java
package com.journal.learn.Journal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "journal_entries")
public class JournalEntry {
     @Id
     private String id;
     private String title;
     private String content;
    private LocalDateTime registeredTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(LocalDateTime registeredTime) {
        this.registeredTime = registeredTime;
    }

    public String getJournalId() {
        return id;
    }

    public void setJournalId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

   }
