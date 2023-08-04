package com.java.projectfinal.model.dto;

import com.java.projectfinal.model.entity.Genre;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MovieDetailsDto {
    private String director;

    private int releaseYear;

    private String title;

    private Genre genre;

}
