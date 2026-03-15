package com.starter.StarterApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greet {
    @GetMapping(path = "")
    public String greet() {
        return "Hello World of Spring Boot";
    }
}
