package com.example.Rent_a_Car.config;


import com.example.Rent_a_Car.model.dto.CarRegisterDTO;
import com.example.Rent_a_Car.model.entity.CarEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

}
