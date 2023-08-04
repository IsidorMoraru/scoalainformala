package com.java.projectfinal.controller;

import com.java.projectfinal.model.dto.MovieDto;
import com.java.projectfinal.model.entity.Genre;
import com.java.projectfinal.model.entity.Movie;
import com.java.projectfinal.model.payload.FilterParams;
import com.java.projectfinal.repository.MovieRepository;
import com.java.projectfinal.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;


    @GetMapping("/addmovie")
    public String showCreateMovieForm(Model model) {
        model.addAttribute("movieDto", new MovieDto());
        return "create-movie";
    }

    @PostMapping("/addmovie")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String createMovie(@ModelAttribute("movieDto") MovieDto movieDto) {
        movieService.createMovie(movieDto);
        return "redirect:/admin/movies";
    }

    @GetMapping("/find-by-title/{title}")
    public ResponseEntity<List<Movie>> getMovieByTitle(@PathVariable String title) {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMovie(@PathVariable UUID id) {
        return movieService.deleteMovie(id);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieDto>> getMoviesByGenre(@PathVariable("genre") Genre genre) {
        List<MovieDto> movies = movieService.getMoviesByGenre(genre);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getmovies")
    public Page<MovieDto> getAllMovies(@RequestParam("page") int page,
                                       @RequestParam("results") int results,
                                       @RequestParam("sort") String sort,
                                       @RequestParam("dir") String dir) {
        return movieService.getAllMovies(FilterParams.builder().page(page)
                .results(results)
                .sort(sort)
                .dir(dir)
                .build()
        );
    }

}
