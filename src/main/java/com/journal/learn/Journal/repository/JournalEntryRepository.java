
//JournalEntryRepository.java
package com.journal.learn.Journal.repository;

import com.journal.learn.Journal.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {
}
