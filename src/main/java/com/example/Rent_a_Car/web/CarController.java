package com.example.Rent_a_Car.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    @GetMapping("car-details")
    public String rentACarDetails(Model model){
       // model.addAttribute();
        return "car-details";
    }
}
