//UserRepository.java
package com.journal.learn.Journal.repository;

import com.journal.learn.Journal.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByUsername(String username);
}
