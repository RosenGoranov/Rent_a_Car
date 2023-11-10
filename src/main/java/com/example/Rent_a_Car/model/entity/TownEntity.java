package com.example.Rent_a_Car.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "towns")
@NamedEntityGraph(
        name = "TownWithAddress",attributeNodes = @NamedAttributeNode("address")

)
public class TownEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,mappedBy = "town")
    private List<AddressEntity> address;

    public long getId() {
        return id;
    }

    public TownEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TownEntity setName(String name) {
        this.name = name;
        return this;
    }
}
