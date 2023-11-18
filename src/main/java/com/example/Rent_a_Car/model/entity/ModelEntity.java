package com.example.Rent_a_Car.model.entity;


import jakarta.persistence.*;




@Entity
@Table(name = "models")
public class ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private BrandEntity brand;

    public long getId() {
        return id;
    }

    public ModelEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ModelEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }
}
