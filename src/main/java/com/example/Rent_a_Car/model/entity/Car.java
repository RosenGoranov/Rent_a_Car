package com.example.Rent_a_Car.model.entity;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Model model;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Transmission transmission;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private FuelType fuelType;

    @Column(nullable = false)
    private String vinNumber;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column
    private LocalDate regDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal rentPerDay;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    public UserEntity userEntity;

    private boolean isReserved;

    public Car() {
    }

    public Car(long id, Model model, Transmission transmission, FuelType fuelType, String vinNumber, String plate, LocalDate regDate, BigDecimal rentPerDay, String description, UserEntity userEntity, boolean isReserved) {
        this.id = id;
        this.model = model;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.vinNumber = vinNumber;
        this.plate = plate;
        this.regDate = regDate;
        this.rentPerDay = rentPerDay;
        this.description = description;
        this.userEntity = userEntity;
        this.isReserved = isReserved;
    }

    public long getId() {
        return id;
    }

    public Car setId(long id) {
        this.id = id;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Car setModel(Model model) {
        this.model = model;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Car setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public Car setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public Car setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
        return this;
    }

    public String getPlate() {
        return plate;
    }

    public Car setPlate(String plate) {
        this.plate = plate;
        return this;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public Car setRegDate(LocalDate regDate) {
        this.regDate = regDate;
        return this;
    }

    public BigDecimal getRentPerDay() {
        return rentPerDay;
    }

    public Car setRentPerDay(BigDecimal rentPerDay) {
        this.rentPerDay = rentPerDay;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Car setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public Car setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public Car setReserved(boolean reserved) {
        isReserved = reserved;
        return this;
    }
}
