package com.example.Rent_a_Car.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Town town;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Street street;

    @Column(nullable = false)
    private String number;







    public static Address builder() {
        return new Address();
    }

    public long getId() {
        return id;
    }



    public Town getTown() {
        return town;
    }

    public Address setTown(Town town) {
        this.town = town;
        return this;
    }

    public Street getStreet() {
        return street;
    }

    public Address setStreet(Street street) {
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
