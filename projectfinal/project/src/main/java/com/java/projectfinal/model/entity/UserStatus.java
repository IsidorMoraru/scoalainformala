package com.java.projectfinal.model.entity;


import java.util.Optional;

public enum UserStatus {
    INACTIVE,
    ACTIVE,
    LOCKED,
    DELETED,
    EXPIRED;

    public Optional<UserStatus> fromString(String name) {
        for (UserStatus us : UserStatus.values()) {
            if (us.name().equals(name)) {
                return Optional.of(us);
            }
        }
        return Optional.empty();
    }
}

