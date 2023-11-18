package com.example.Rent_a_Car.repository;


import com.example.Rent_a_Car.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findAllByModelBrandName(String brand);
}
