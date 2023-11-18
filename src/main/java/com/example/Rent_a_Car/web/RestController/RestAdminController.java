package com.example.Rent_a_Car.web.RestController;


import com.example.Rent_a_Car.model.dto.CarRegisterDTO;
import com.example.Rent_a_Car.model.dto.UserDTO;
import com.example.Rent_a_Car.services.UserService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class RestAdminController {

    private UserService userService;

    public RestAdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @PostMapping("add_car")
    public ResponseEntity<CarRegisterDTO> addNewCar(@RequestBody @Valid CarRegisterDTO newCar, BindingResult result) {
        return null;//TODO
    }


}
