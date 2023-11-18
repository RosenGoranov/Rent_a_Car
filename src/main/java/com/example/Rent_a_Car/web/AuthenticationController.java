package com.example.Rent_a_Car.web;

import com.example.Rent_a_Car.model.auth.AuthenticationRequest;
import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.model.dto.AddressDTO;
import com.example.Rent_a_Car.model.dto.UserDTO;
import com.example.Rent_a_Car.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private static final String SPRING_ATTRIBUTE_REQUEST = "org.springframework.validation.BindingResult.registerRequest";
    private static final String SPRING_ATTRIBUTE_ADDRESS = "org.springframework.validation.BindingResult.addressDTO";
    private final UserService userService;
    private final SecurityContextRepository securityContextRepository;


    @Autowired
    public AuthenticationController(UserService userService, SecurityContextRepository securityContextRepository) {

        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
    }


    @ModelAttribute(name = "registerRequest")
    public RegisterRequest initRequest() {
        return new RegisterRequest();
    }


    @ModelAttribute(name = "address")
    public AddressDTO initAddress() {
        return new AddressDTO();
    }

    @ModelAttribute(name = "loginRequest")
    public AuthenticationRequest loginRequest() {
        return new AuthenticationRequest();
    }


    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid RegisterRequest registerRequest,
            BindingResult bindingResultUser,
            @Valid AddressDTO addressDTO,
            BindingResult bindingResultAddress,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        registerRequest.setAddressDTO(addressDTO);
        if (bindingResultUser.hasErrors() || bindingResultAddress.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerRequest", registerRequest);
            redirectAttributes.addFlashAttribute(SPRING_ATTRIBUTE_REQUEST, bindingResultUser);
            redirectAttributes.addFlashAttribute("addressDTO", addressDTO);
            redirectAttributes.addFlashAttribute(SPRING_ATTRIBUTE_ADDRESS, bindingResultAddress);

            return "redirect:/auth/register";
        }
        try {
            UserDTO byEmail = userService.getByEmail(registerRequest.getEmail());
            if (byEmail != null) {
                redirectAttributes.addFlashAttribute("emailExist", true);
                return "redirect:/auth/login";
            }

        } catch (UsernameNotFoundException e) {
            this.userService.save(registerRequest, successfulAuth -> {
                SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

                SecurityContext context = strategy.createEmptyContext();
                context.setAuthentication(successfulAuth);

                strategy.setContext(context);

                securityContextRepository.saveContext(context, request, response);
            });


        }


        return "redirect:/";
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/auth/login";
    }


}
