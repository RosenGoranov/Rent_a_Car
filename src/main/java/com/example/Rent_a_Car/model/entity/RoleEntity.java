package com.example.Rent_a_Car.model.entity;


import com.example.Rent_a_Car.model.enums.RoleEnum;
import jakarta.persistence.*;



@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;


    public RoleEntity() {
    }

    public RoleEntity(RoleEnum name) {
        this.name = name;
    }



    public long getId() {
        return id;
    }

    public RoleEntity setId(long id) {
        this.id = id;
        return this;
    }

    public RoleEnum getName() {
        return name;
    }

    public RoleEntity setName(RoleEnum name) {
        this.name = name;
        return this;
    }
}
