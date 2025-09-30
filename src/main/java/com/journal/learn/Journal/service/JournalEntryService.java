package com.journal.learn.Journal.service;

import com.journal.learn.Journal.entity.JournalEntry;
import com.journal.learn.Journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    // Get all entries
    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    // Save (create or update) entry
    public JournalEntry saveEntry(JournalEntry journalEntry) {
        if (journalEntry.getRegisteredTime() == null) {
            journalEntry.setRegisteredTime(LocalDateTime.now());
        }
        return journalEntryRepository.save(journalEntry);
    }

    // Get entry by id
    public JournalEntry getEntryById(String id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    // Delete entry
    public void deleteEntry(String id) {
        journalEntryRepository.deleteById(id);
    }

}
