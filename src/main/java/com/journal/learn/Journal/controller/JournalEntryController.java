package com.journal.learn.Journal.controller;

import com.journal.learn.Journal.entity.JournalEntry;
import com.journal.learn.Journal.entity.User;
import com.journal.learn.Journal.service.JournalEntryService;
import com.journal.learn.Journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    // Get all entries of the user
    @GetMapping("/{username}")
    public List<JournalEntry> getAllEntriesByUsername(@PathVariable String username) {
        User user = userService.findbyUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        return user.getJournalEntries();
    }

    // Add a new entry for a user
    @PostMapping("/{username}")
    public JournalEntry insertEntryToUser(@RequestBody JournalEntry journalEntry, @PathVariable String username) {
        journalEntry.setRegisteredTime(LocalDateTime.now());
        JournalEntry savedEntry = journalEntryService.saveEntry(journalEntry);

        User existingUser = userService.findbyUsername(username);
        if (existingUser == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        existingUser.getJournalEntries().add(savedEntry);
        userService.saveUser(existingUser);

        return savedEntry;
    }

    // Delete a specific entry for a user
    @DeleteMapping("/{username}/{entryId}")
    public String deleteEntryById(@PathVariable String username, @PathVariable String entryId) {
        User existingUser = userService.findbyUsername(username);
        if (existingUser == null) {
            throw new RuntimeException("User not found: " + username);
        }

        JournalEntry existingEntry = journalEntryService.getEntryById(entryId);
        if (existingEntry == null) {
            throw new RuntimeException("Entry not found: " + entryId);
        }

        // remove entry reference from user
        existingUser.getJournalEntries().removeIf(j -> j.getJournalId().equals(entryId));
        userService.saveUser(existingUser);

        // delete entry from DB
        journalEntryService.deleteEntry(entryId);

        return "Deleted journal entry with id: " + entryId;
    }

    // Update a specific entry for a user
    @PutMapping("/{username}/{entryId}")
    public JournalEntry updateJournalEntryForUser(@PathVariable String username,
                                                  @PathVariable String entryId,
                                                  @RequestBody JournalEntry updatedEntry) {

        User user = userService.findbyUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        JournalEntry existingEntry = journalEntryService.getEntryById(entryId);
        if (existingEntry == null) {
            throw new RuntimeException("Entry not found with id: " + entryId);
        }

        // Update fields if provided
        if (updatedEntry.getContent() != null && !updatedEntry.getContent().isEmpty()) {
            existingEntry.setContent(updatedEntry.getContent());
        }
        if (updatedEntry.getTitle() != null && !updatedEntry.getTitle().isEmpty()) {
            existingEntry.setTitle(updatedEntry.getTitle());
        }

        JournalEntry savedEntry = journalEntryService.saveEntry(existingEntry);

        // ensure user still references updated entry
        user.getJournalEntries().removeIf(j -> j.getJournalId().equals(entryId));
        user.getJournalEntries().add(savedEntry);
        userService.saveUser(user);

        return savedEntry;
    }

}
