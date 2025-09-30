//UserService.java
package com.journal.learn.Journal.service;

import com.journal.learn.Journal.entity.User;
import com.journal.learn.Journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userrepository;
    private static final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

    public List<User> getAllUsers(){
        return userrepository.findAll();
    }

    public User saveUser(User user){
        userrepository.save(user);
        user.setRoles(Arrays.asList("USER"));
        return user;
    }
    public User saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userrepository.save(user);
    }

    public boolean deleteUserById(String id){
        userrepository.deleteById(id);
        return true;
    }

    public User getUserById(String id){
        return userrepository.findById(id).orElse(null);
    }
    public User findbyUsername(String username){
        return userrepository.findByUsername(username);
    }

}
