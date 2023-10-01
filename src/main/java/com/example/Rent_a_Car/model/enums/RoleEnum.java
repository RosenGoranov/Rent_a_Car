package com.example.Rent_a_Car.model.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.Rent_a_Car.model.enums.UserPermissionEnum.*;

public enum RoleEnum {
    USER(Sets.newHashSet(CREATE, UPDATE)),

    Employee(Sets.newHashSet(UPDATE)),
    ADMIN(Sets.newHashSet(CREATE, UPDATE, DELETE, READS));


    private final Set<UserPermissionEnum> permissions;

    RoleEnum(Set<UserPermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissionEnum> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().
                stream().
                map(permission -> new SimpleGrantedAuthority(permission.getPermission())).
                collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;

    }
}
