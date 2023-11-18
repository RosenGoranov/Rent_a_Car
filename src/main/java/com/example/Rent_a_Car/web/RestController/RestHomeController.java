package com.example.Rent_a_Car.web.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class RestHomeController {

    @GetMapping("/api/v1/index")
    public ResponseEntity<Object> home(){
        return ResponseEntity.ok().body("HOME PAGES");
    }

}
