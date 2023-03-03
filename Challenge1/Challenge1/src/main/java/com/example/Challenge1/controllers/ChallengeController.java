package com.example.Challenge1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChallengeController {

    @GetMapping("/welcome")
    public String helloWorld(){
        return "Hello World";
    }
}
