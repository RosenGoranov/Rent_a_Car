package com.example.Rent_a_Car.web.mvccontroller;

import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.model.dto.AddressDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {


    @ModelAttribute(name = "registerRequest")
    public RegisterRequest initRequest() {
        return new RegisterRequest();
    }

    @ModelAttribute(name = "principal")
    public Principal initPrincipal(Principal principal) {
        return principal;
    }

    @ModelAttribute(name = "address")
    public AddressDTO initAddress(){
        return new AddressDTO();
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid RegisterRequest registerRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registerRequest",registerRequest);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerRequest",bindingResult);

            return "register";
        }

        return "redirect:/login";
    }


}
