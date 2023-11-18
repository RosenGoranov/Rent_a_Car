package com.example.Rent_a_Car.web;

import com.example.Rent_a_Car.model.dto.CarRegisterDTO;
import com.example.Rent_a_Car.services.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ModelAttribute("registerCar")
    public CarRegisterDTO newCar() {
        return new CarRegisterDTO();
    }

    @GetMapping("car-details")
    public String rentACarDetails() {

        return "car-details";
    }

}
