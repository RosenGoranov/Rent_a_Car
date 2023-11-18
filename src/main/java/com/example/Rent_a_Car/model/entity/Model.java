package com.example.Rent_a_Car.model.entity;


import com.example.Rent_a_Car.model.enums.ModelEnum;
import jakarta.persistence.*;



@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModelEnum name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Brand brand;

    public Model() {
    }

    public Model(long id, ModelEnum name, Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public long getId() {
        return id;
    }

    public Model setId(long id) {
        this.id = id;
        return this;
    }

    public ModelEnum getName() {
        return name;
    }

    public Model setName(ModelEnum name) {
        this.name = name;
        return this;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }
}
