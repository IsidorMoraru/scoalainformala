package com.java.projectfinal.model.entity;

import java.util.Optional;

public enum UserRole {
    USER,
    ADMIN,
    SYSTEM;

    public Optional<UserRole> fromString(String name) {
        for (UserRole ur : UserRole.values()) {
            if (ur.name().equals(name)) {
                return Optional.of(ur);
            }
        }
        return Optional.empty();
    }
}
