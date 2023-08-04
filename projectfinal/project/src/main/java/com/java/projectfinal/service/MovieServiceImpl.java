package com.java.projectfinal.service;

import com.java.projectfinal.model.dto.MovieDetailsDto;
import com.java.projectfinal.model.dto.MovieDto;
import com.java.projectfinal.model.entity.Genre;
import com.java.projectfinal.model.entity.Movie;
import com.java.projectfinal.model.mapper.MovieMapper;
import com.java.projectfinal.model.payload.FilterParams;
import com.java.projectfinal.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Page<MovieDto> getAllMovies(FilterParams params) {
        Sort sort = Sort.by(Sort.Direction.fromString(params.getDir()), params.getSort());
        return movieRepository.findAll(
                        PageRequest.of(params.getPage(), params.getResults(), sort)
                )
                .map(movieMapper::toDto);
    }


    @Override
    public boolean createMovie(MovieDto movieDto) {
        return movieRepository.findByTitle(movieDto.getTitle()).map(book -> {

            movieRepository.save(book);
            return true;
        }).orElseGet(() -> {
            Movie movie = movieMapper.toEntity(movieDto);
            movieRepository.save(movie);
            return movie.getId() != null;
        });
    }

    @Override
    public Optional<MovieDto> getMovieById(UUID id) {
        return movieRepository.findById(id).map(movieMapper::toDto);
    }

    @Override
    public Optional<MovieDetailsDto> getMovieDetails(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<MovieDto> getMoviesByGenre(Genre genre) {

        return movieRepository.findByGenre(genre).stream().map(movieMapper::toDto).toList();
    }


    @Override
    public boolean updateMovie(UUID id, MovieDto movieDto) {
        return movieRepository.findById(id).map(existingMovie -> {
            MovieMapper.INSTANCE.updateEntityFromDto(movieDto, existingMovie);
            movieRepository.save(existingMovie);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteMovie(UUID id) {
        return movieRepository.findById(id).map(movie -> {
            movieRepository.save(movie);
            return true;
        }).orElse(false);
    }
}
