//UserController.java
package com.journal.learn.Journal.controller;

import com.journal.learn.Journal.entity.JournalEntry;
import com.journal.learn.Journal.entity.User;
import com.journal.learn.Journal.service.JournalEntryService;
import com.journal.learn.Journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userservice;

    @GetMapping
    public List<User> getAllUsers(){
        return userservice.getAllUsers();
    }

    @GetMapping("id/{id}")
    public User GetUserById(@PathVariable String id){
        return userservice.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userservice.saveNewUser(user);
    }

    @DeleteMapping("/id/{id}")
    public boolean DeleteUserById(@PathVariable String id){
        userservice.deleteUserById(id);
        return true;
    }

    @PutMapping("/id/{id}")
    public User UpdateUserById(@PathVariable String id, @RequestBody User user) {
        User existinguser = userservice.getUserById(id);
        if (existinguser != null) {
            existinguser.setUsername(user.getUsername());
            existinguser.setPassword(user.getPassword());
            return userservice.saveUser(existinguser); // save updated user
        }
        throw new RuntimeException("user not found with id: " + id);
    }

}