package com.example.Rent_a_Car.model.entity;


import com.example.Rent_a_Car.model.enums.RoleEnum;
import jakarta.persistence.*;



@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;


    public Role() {
    }

    public Role(long id, RoleEnum name) {
        this.id = id;
        this.name = name;
    }



    public long getId() {
        return id;
    }

    public Role setId(long id) {
        this.id = id;
        return this;
    }

    public RoleEnum getName() {
        return name;
    }

    public Role setName(RoleEnum name) {
        this.name = name;
        return this;
    }
}
