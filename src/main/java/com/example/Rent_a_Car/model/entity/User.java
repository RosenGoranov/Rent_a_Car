package com.example.Rent_a_Car.model.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User extends BasePerson {

    @Column
    private boolean isAccountNonExpired;

    @Column
    private boolean isAccountNonLocked;


    public User() {
        super();
    }

    public User(boolean isAccountNonExpired, boolean isAccountNonLocked) {
        super();
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
    }


    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public User setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
        return this;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public User setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
        return this;
    }
}
