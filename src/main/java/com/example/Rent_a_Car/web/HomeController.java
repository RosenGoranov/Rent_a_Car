package com.example.Rent_a_Car.web;

import com.example.Rent_a_Car.services.AppUserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(@AuthenticationPrincipal AppUserDetail appUserDetails, Model model) {

        if (appUserDetails != null) {
            model.addAttribute("fullName", appUserDetails.getFullName());
            model.addAttribute("address", appUserDetails.getAddress());

        }

        return "/index";
    }

    @GetMapping("/pages/users")
    public String users() {
        return "user-pages";
    }

    //TODO implement other controller
    @GetMapping("/pages/admin")
    public String admin() {
        return "admin-pages";
    }

    @GetMapping("/pages/moderator")
    public String moderator() {
        return "moderator-pages";
    }

    @GetMapping("/pages/employee")
    public String employee() {
        return "employee-pages";
    }
}
