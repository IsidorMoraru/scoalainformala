package com.java.projectfinal.model.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String director;
    private int releaseYear;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
