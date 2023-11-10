package com.example.Rent_a_Car.model.entity;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "brands")
@NamedEntityGraph(
        name = "brandWithModels",
        attributeNodes = @NamedAttributeNode("models")
)
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<ModelEntity> models;

    public long getId() {
        return id;
    }

    public BrandEntity setId(long id) {

        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(List<ModelEntity> modelEntities) {
        this.models = modelEntities;
        return this;
    }
}


