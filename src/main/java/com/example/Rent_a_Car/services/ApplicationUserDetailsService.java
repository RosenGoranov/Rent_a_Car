package com.example.Rent_a_Car.services;

import com.example.Rent_a_Car.model.entity.RoleEntity;
import com.example.Rent_a_Car.model.entity.UserEntity;
import com.example.Rent_a_Car.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository
                .findByEmail(email)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("UserEntity with name " + email + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        String address = userEntity.getAddress().getTown() + "\n" +
                userEntity.getAddress().getStreet() + "\n" +
                userEntity.getAddress().getNumber();
        return new AppUserDetail(
                userEntity.getEmail(),
                userEntity.getPassword(),
                extractAuthorities(userEntity))
                .setId(userEntity.getId())
                .setFullName(
                        userEntity.getFirstName() + " " + userEntity.getLastName()
                )
                .setAddress(address);
    }

    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
        return userEntity.getRole().stream().map(this::mapRole).collect(Collectors.toList());

    }

    private GrantedAuthority mapRole(RoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getName().name());
    }
}