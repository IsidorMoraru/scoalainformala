package com.java.projectfinal.service;

import com.java.projectfinal.model.dto.MovieDetailsDto;
import com.java.projectfinal.model.dto.MovieDto;
import com.java.projectfinal.model.entity.Genre;
import com.java.projectfinal.model.payload.FilterParams;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {
    Page<MovieDto> getAllMovies(FilterParams params);

    Optional<MovieDto> getMovieById(UUID id);

    Optional<MovieDetailsDto> getMovieDetails(UUID id);

    List<MovieDto> getMoviesByGenre(Genre genre);
    boolean deleteMovie(UUID id);

    boolean updateMovie(UUID id, MovieDto movieDto);

    boolean createMovie(MovieDto movieDto);
}
