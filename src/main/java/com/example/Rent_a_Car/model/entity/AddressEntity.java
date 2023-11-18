package com.example.Rent_a_Car.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,optional = false)
    private TownEntity town;

    @Column( nullable = true)
    private String street;

    @Column(nullable = false)
    private String number;


    public long getId() {
        return id;
    }

    public AddressEntity setId(long id) {
        this.id = id;
        return this;
    }

    public TownEntity getTown() {
        return town;
    }

    public AddressEntity setTown(TownEntity town) {
        this.town = town;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public AddressEntity setNumber(String number) {
        this.number = number;
        return this;
    }
}
