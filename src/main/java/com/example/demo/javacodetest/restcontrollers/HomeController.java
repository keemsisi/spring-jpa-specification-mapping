package com.example.demo.javacodetest.restcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
public class HomeController {

    @GetMapping(value = "/")
    public ResponseEntity<String> home(){
        return ResponseEntity.of(Optional.of("Hello"));
    }
}
