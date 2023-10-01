package com.example.Rent_a_Car.model.enums;


public enum UserPermissionEnum {
    CREATE("create"), READS("reads"),DELETE("delete"),UPDATE("update");

    private final String permission;

    UserPermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
