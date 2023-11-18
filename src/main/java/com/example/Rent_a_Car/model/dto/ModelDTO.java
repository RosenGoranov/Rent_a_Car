package com.example.Rent_a_Car.model.dto;

public class ModelDTO {
    private Long id;
    private String name;

    public long getId() {
        return id;
    }

    public ModelDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelDTO setName(String name) {
        this.name = name;
        return this;
    }


}
