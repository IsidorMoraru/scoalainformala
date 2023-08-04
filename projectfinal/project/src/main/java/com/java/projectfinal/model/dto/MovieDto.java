package com.java.projectfinal.model.dto;

import com.java.projectfinal.model.entity.Genre;
import lombok.Data;

@Data
public class MovieDto {
    String director;
    String title;
    int year;
    Genre genre;
}
