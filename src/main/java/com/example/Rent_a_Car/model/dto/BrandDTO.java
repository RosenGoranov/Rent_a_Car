package com.example.Rent_a_Car.model.dto;

import java.util.List;

public class BrandDTO {

    private long id;
    private String name;

    private List<ModelDTO> model;

    public long getId() {
        return id;
    }

    public BrandDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelDTO> getModel() {
        return model;
    }

    public BrandDTO setModel(List<ModelDTO> model) {
        this.model = model;
        return this;
    }
}
