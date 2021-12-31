package com.example.crud.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWord {

    @GetMapping
    public ResponseEntity getHelloWord() {
        return ResponseEntity.ok("Hello-Word");
    }
}
