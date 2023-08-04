package com.java.projectfinal.model.entity;

import java.util.Optional;

public enum Genre {
    ACTION,
    COMEDY,
    DRAMA,
    HORROR,
    ROMANCE,
    SCIFI;


    public static Optional<Genre> fromString(String name) {
        for (Genre genre : Genre.values()) {
            if (genre.name().equalsIgnoreCase(name)) {
                return Optional.of(genre);
            }
        }
        return Optional.empty();
    }
}
