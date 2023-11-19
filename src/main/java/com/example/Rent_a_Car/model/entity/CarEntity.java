package com.example.Rent_a_Car.model.entity;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private ModelEntity model;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private TransmissionEntity transmission;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private FuelTypeEntity fuelType;

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

    private String imgURL;

    public long getId() {
        return id;
    }

    public CarEntity setId(long id) {
        this.id = id;
        return this;
    }

    public ModelEntity getModel() {
        return model;
    }

    public CarEntity setModel(ModelEntity model) {
        this.model = model;
        return this;
    }

    public TransmissionEntity getTransmission() {
        return transmission;
    }

    public CarEntity setTransmission(TransmissionEntity transmission) {
        this.transmission = transmission;
        return this;
    }

    public FuelTypeEntity getFuelType() {
        return fuelType;
    }

    public CarEntity setFuelType(FuelTypeEntity fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public CarEntity setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
        return this;
    }

    public String getPlate() {
        return plate;
    }

    public CarEntity setPlate(String plate) {
        this.plate = plate;
        return this;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public CarEntity setRegDate(LocalDate regDate) {
        this.regDate = regDate;
        return this;
    }

    public BigDecimal getRentPerDay() {
        return rentPerDay;
    }

    public CarEntity setRentPerDay(BigDecimal rentPerDay) {
        this.rentPerDay = rentPerDay;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CarEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public CarEntity setReserved(boolean reserved) {
        isReserved = reserved;
        return this;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
