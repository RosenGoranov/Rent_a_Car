package com.example.Rent_a_Car.model.entity;

import jakarta.persistence.*;


import java.util.Collection;
import java.util.Objects;


@MappedSuperclass
public abstract class BasePerson {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private AddressEntity addressEntity;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Collection<RoleEntity> roleEntity;


    public long getId() {
        return id;
    }

    public BasePerson setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BasePerson setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BasePerson setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BasePerson setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BasePerson setPassword(String password) {
        this.password = password;
        return this;
    }

    public AddressEntity getAddress() {
        return addressEntity;
    }

    public BasePerson setAddress(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
        return this;
    }

    public Collection<RoleEntity> getRole() {
        return roleEntity;
    }

    public BasePerson setRole(Collection<RoleEntity> roleEntity) {
        this.roleEntity = roleEntity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasePerson that = (BasePerson) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password);
    }





}
