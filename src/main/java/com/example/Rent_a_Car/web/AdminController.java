package com.example.Rent_a_Car.web;

import com.example.Rent_a_Car.model.dto.CarRegisterDTO;
import com.example.Rent_a_Car.services.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CarService carService;

    public AdminController(CarService carService) {
        this.carService = carService;
    }

    @ModelAttribute("newCar")
    public CarRegisterDTO newCar(){
        return new CarRegisterDTO();
    }

    @GetMapping("/panel")
    public String adminPanel() {
        return "admin-page";
    }

    @GetMapping("/new-car")
    public String addNewCar() {
        return "add-car";
    }

    @PostMapping("/new-car")
    public String addNawCar(@Valid CarRegisterDTO newCar,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newCar", newCar)
                    .addFlashAttribute("org.springframework.validation.BindingResult.newCar", bindingResult);
            return "redirect:/admin/new-car";
        }

        this.carService.addNewCar(newCar);
        return "redirect:/admin/panel";
    }
}
