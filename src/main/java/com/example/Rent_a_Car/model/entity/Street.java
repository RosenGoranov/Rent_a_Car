package com.example.Rent_a_Car.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "streets")
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public Street setName(String name) {
        this.name = name;
        return this;
    }
}
