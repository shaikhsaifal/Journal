package com.journal.learn.Journal;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingFIle {
    @GetMapping
    public String getmethod(){
        return "working!";
    }
}
