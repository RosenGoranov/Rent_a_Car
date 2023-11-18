package com.example.Rent_a_Car.model.entity;


import com.example.Rent_a_Car.model.enums.FuelTypeEnums;
import jakarta.persistence.*;



@Entity
@Table(name = "fuel_type")
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelTypeEnums name;

    public FuelType() {
    }

    public FuelType(long id, FuelTypeEnums name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public FuelType setId(long id) {
        this.id = id;
        return this;
    }

    public FuelTypeEnums getName() {
        return name;
    }

    public FuelType setName(FuelTypeEnums name) {
        this.name = name;
        return this;
    }
}
