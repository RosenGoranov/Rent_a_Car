package com.example.Rent_a_Car.web.RestController;

import com.example.Rent_a_Car.model.auth.AuthenticationRequest;
import com.example.Rent_a_Car.model.auth.AuthenticationResponse;
import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")

public class RestAuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public RestAuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }



    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ) {

      return ResponseEntity.ok(authenticationService.register(request));

    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ) {
            AuthenticationResponse authenticate = authenticationService.authenticate(request);
            return ResponseEntity.ok(authenticate);
    }


}
