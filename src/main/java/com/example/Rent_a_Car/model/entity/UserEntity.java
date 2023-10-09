package com.example.Rent_a_Car.model.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity extends BasePerson {

    @Column
    private boolean isAccountNonExpired;

    @Column
    private boolean isAccountNonLocked;


    public UserEntity() {
        super();
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public UserEntity setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
        return this;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public UserEntity setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
        return this;
    }


}
