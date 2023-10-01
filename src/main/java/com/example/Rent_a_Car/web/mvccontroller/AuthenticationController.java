package com.example.Rent_a_Car.web.mvccontroller;

import com.example.Rent_a_Car.model.auth.AuthenticationResponse;
import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.model.dto.AddressDTO;
import com.example.Rent_a_Car.services.AuthenticationService;
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

    private static final String SPRING_ATTRIBUTE_REQUEST = "org.springframework.validation.BindingResult.registerRequest";
    private static final String SPRING_ATTRIBUTE_ADDRESS = "org.springframework.validation.BindingResult.address";

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @ModelAttribute(name = "registerRequest")
    public RegisterRequest initRequest() {
        return new RegisterRequest();
    }

    @ModelAttribute(name = "principal")
    public Principal initPrincipal(Principal principal) {
        return principal;
    }

    @ModelAttribute(name = "address")
    public AddressDTO initAddress() {
        return new AddressDTO();
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid RegisterRequest registerRequest,
            @Valid AddressDTO address,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            errorsInFormFields(
                    registerRequest,
                    bindingResult,
                    redirectAttributes,
                    SPRING_ATTRIBUTE_REQUEST,
                    SPRING_ATTRIBUTE_ADDRESS);

            return "register";
        }

        registerRequest.setAddressDTO(address);
        AuthenticationResponse token = this.authenticationService.register(registerRequest);


        return "redirect:/login";
    }

    private static void errorsInFormFields(
            RegisterRequest registerRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            String request,
            String address) {
        redirectAttributes.addFlashAttribute("registerRequest", registerRequest);
        redirectAttributes.addFlashAttribute(request, bindingResult);
        redirectAttributes.addFlashAttribute(address, bindingResult);
    }


}
