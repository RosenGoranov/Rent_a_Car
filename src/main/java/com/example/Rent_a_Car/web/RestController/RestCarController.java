package com.example.Rent_a_Car.web.RestController;

import com.example.Rent_a_Car.model.dto.RentCarDTO;
import com.example.Rent_a_Car.model.enums.BrandEnum;
import com.example.Rent_a_Car.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/home/cars")

public class RestCarController {


    private final CarService carService;

    public RestCarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/brands")
    public ResponseEntity<BrandEnum[]> chooseAvailableBrand(){
        return ResponseEntity.ok().body(BrandEnum.values());
    }


    @PostMapping("/rent{b}{m}{t}{f}{r}")
    public ResponseEntity<RentCarDTO> rentCar(@RequestParam(value = "b") String brand,
                                          @RequestParam(required = false, value = "m") String model,
                                          @RequestParam(required = false, value = "t") String transmission,
                                          @RequestParam(required = false, value = "f") String fuelType,
                                          @RequestParam(required = false, value = "r") String rentPerDay
    ) {

        List<RentCarDTO> cars = this.carService.findAll(brand);
        return null;
    }
}
