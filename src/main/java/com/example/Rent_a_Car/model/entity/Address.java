package com.example.Rent_a_Car.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String town;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;


    private Address() {

    }


    public Address(String town, String street, String number) {
        this();
        this.town = town;
        this.street = street;
        this.number = number;
    }

    public static Address builder() {
        return new Address();
    }

    public long getId() {
        return id;
    }

    public Address setId(long id) {
        this.id = id;
        return this;
    }

    public String getTown() {
        return town;
    }

    public Address setTown(String town) {
        this.town = town;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Address setNumber(String number) {
        this.number = number;
        return this;
    }
}
