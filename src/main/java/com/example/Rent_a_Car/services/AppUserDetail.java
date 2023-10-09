package com.example.Rent_a_Car.services;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;


public class AppUserDetail extends User {

    private String address;
    private String fullName;


    public AppUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


    public String getAddress() {
        return address;
    }

    public AppUserDetail setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public AppUserDetail setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

}
