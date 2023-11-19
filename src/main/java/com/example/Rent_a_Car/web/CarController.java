package com.example.Rent_a_Car.web;

import com.example.Rent_a_Car.model.dto.CarDTO;
import com.example.Rent_a_Car.services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }



    @GetMapping("/{brand}")
    public String getAllCarByBrand(@PathVariable String brand,
                                   Model model,
                                   @PageableDefault(size = 6,
                                   sort = "rentPerDay")
                                       Pageable pageable
                                   ) {
        Page<CarDTO> allCarByBrand = carService.allByBrand( brand,pageable);
        model.addAttribute("brand",brand.toUpperCase());
        model.addAttribute("cars",allCarByBrand);

        return "car-by-brand";
    }
}
