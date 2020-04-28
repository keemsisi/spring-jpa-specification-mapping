package com.example.demo.javacodetest.restcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.Optional;

public class HomeController {

    @RequestMapping(name = "/" , method = RequestMethod.GET)
    public ResponseEntity home(){
        return ResponseEntity.of(Optional.of("Hello"));
    }
}
