package com.example.Rent_a_Car.web.mvccontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class homeController {

    @GetMapping("/home")
    public String authUser(){
        return "redirect:/home";
    }

}
