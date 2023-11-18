package com.example.Rent_a_Car.model.entity;



import com.example.Rent_a_Car.model.enums.BrandEnum;
import jakarta.persistence.*;



@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BrandEnum name;


    public Brand() {
    }

    public Brand(long id, BrandEnum name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Brand setId(long id) {
        this.id = id;
        return this;
    }

    public BrandEnum getName() {
        return name;
    }

    public Brand setName(BrandEnum name) {
        this.name = name;
        return this;
    }
}
