package com.example.Rent_a_Car.model.dto;

import com.example.Rent_a_Car.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;



public class UserDTO implements UserDetails {

    private long id;

    private String firstName;

    private String lastName;

    private String email;


    private String password;

    private String confirmPassword;

    private boolean isAccountNonExpired ;

    private boolean isAccountNonLocked;

    private AddressDTO addressDTO;

    private Role role;

    public UserDTO() {
    }

    public UserDTO( String firstName,
                    String lastName,
                    String email,
                    String password,
                    String confirmPassword,
                    boolean isAccountNonExpired,
                    boolean isAccountNonLocked,
                    AddressDTO addressDTO,
                    Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.addressDTO = addressDTO;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public UserDTO setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
        return this;
    }

    public UserDTO setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
        return this;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public UserDTO setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserDTO setRole(Role role) {
        this.role = role;
        return this;
    }

    public static UserDTO builder() {

        return new UserDTO();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getName().getAuthority();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }


}
