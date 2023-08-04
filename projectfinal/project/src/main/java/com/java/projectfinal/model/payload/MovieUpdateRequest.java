package com.java.projectfinal.model.payload;

import com.java.projectfinal.model.entity.Genre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record MovieUpdateRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Director is required")
        @Length(min = 3, message = "The first name must have minimum 3 letters")
        String director,

        @Min(value = 1888, message = "Release year must be at least 1888")
        int releaseYear,
        Genre genre
) {
}
