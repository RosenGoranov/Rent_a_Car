package com.example.Rent_a_Car.web.mvccontroller;

import com.example.Rent_a_Car.model.auth.AuthenticationRequest;
import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.model.dto.AddressDTO;
import com.example.Rent_a_Car.model.dto.UserDTO;
import com.example.Rent_a_Car.services.AuthenticationService;
import com.example.Rent_a_Car.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private static final String SPRING_ATTRIBUTE_LOGIN = "org.springframework.validation.BindingResult.loginRequest";

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
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

    @ModelAttribute(name = "loginRequest")
    public AuthenticationRequest initLoginRequest() {
        return new AuthenticationRequest();
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
            RedirectAttributes redirectAttributes,
            HttpServletResponse servletResponse
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerRequest", registerRequest);
            redirectAttributes.addFlashAttribute(SPRING_ATTRIBUTE_REQUEST, bindingResult);
            redirectAttributes.addFlashAttribute(SPRING_ATTRIBUTE_ADDRESS, bindingResult);

            return "register";
        }
        if (userService.getByEmail(registerRequest.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("emailExist", true);
            return "redirect:/login";
        }
        registerRequest.setAddressDTO(address);
        String token = this.authenticationService.register(registerRequest).getToken();
        Cookie cookie = new Cookie("AUTH_TOKEN", token);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        servletResponse.setHeader("Authorization", ("Bearer " + token));

        return "redirect:/home";
    }


    @GetMapping("login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("login")
    public String login(
            @Valid AuthenticationRequest loginRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpServletResponse servletResponse
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginRequest", loginRequest);
            redirectAttributes.addFlashAttribute(SPRING_ATTRIBUTE_LOGIN, bindingResult);
            return "login";
        }

        try {
            UserDTO byEmail = this.userService.getByEmail(loginRequest.getEmail());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("notExistUser", e.getMessage());
            return "redirect:/register";
        }

        String token = this.authenticationService.authenticate(loginRequest).getToken();
        Cookie cookie = new Cookie("AUT_TOKEN", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        servletResponse.addHeader("Authorization", ("Bearer " + token));
        return "redirect:/home";
    }


}
