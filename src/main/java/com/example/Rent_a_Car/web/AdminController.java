package com.example.Rent_a_Car.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-page")
public class AdminController {

    @GetMapping("/panel")
    public String adminPanel(){
        return "admin-page";
    }

    @GetMapping("new-car")
    public String addNewCar(){
        return "add-car";
    }
}
