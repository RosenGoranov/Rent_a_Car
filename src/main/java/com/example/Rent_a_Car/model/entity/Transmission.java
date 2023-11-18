package com.example.Rent_a_Car.model.entity;


import com.example.Rent_a_Car.model.enums.TransmissionsEnum;
import jakarta.persistence.*;



@Entity
@Table(name = "transmissions")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionsEnum name;

    public Transmission() {
    }

    public Transmission(long id, TransmissionsEnum name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Transmission setId(long id) {
        this.id = id;
        return this;
    }

    public TransmissionsEnum getName() {
        return name;
    }

    public Transmission setName(TransmissionsEnum name) {
        this.name = name;
        return this;
    }
}
