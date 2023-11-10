package com.example.Rent_a_Car.web;

import com.example.Rent_a_Car.model.dto.BrandDTO;
import com.example.Rent_a_Car.model.dto.CarForRentDTO;
import com.example.Rent_a_Car.model.dto.RentCarUserModel;
import com.example.Rent_a_Car.services.AppUserDetail;
import com.example.Rent_a_Car.services.BrandService;
import com.example.Rent_a_Car.services.CarService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    private final BrandService brandService;

    private final CarService carService;

    public HomeController(BrandService brandService, CarService carService) {
        this.brandService = brandService;

        this.carService = carService;
    }

    @ModelAttribute("brands")
    public List<BrandDTO> brands() {
        return this.brandService.getAllBrands();
    }

    @ModelAttribute("carForRent")
    public CarForRentDTO car() {
        return new CarForRentDTO();
    }


    @GetMapping("/")
    public String home(@AuthenticationPrincipal AppUserDetail appUserDetails, Model model) {

        if (appUserDetails != null) {
            model.addAttribute("fullName", appUserDetails.getFullName());
            model.addAttribute("address", appUserDetails.getAddress());

        }

        return "index";
    }

    @PostMapping("/rent-car")
    public String rentACar(@Valid RentCarUserModel rentCarUserModel,
                           BindingResult bindingResult,
                           @AuthenticationPrincipal AppUserDetail appUserDetail,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("rentCar", rentCarUserModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.rentCarDTO", bindingResult);
        }
        CarForRentDTO rent = this.carService.rent(rentCarUserModel, appUserDetail.getId());
        return "redirect:/car-details";
    }


}
