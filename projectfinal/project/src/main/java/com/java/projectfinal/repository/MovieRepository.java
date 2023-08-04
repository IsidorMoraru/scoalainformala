package com.java.projectfinal.repository;

import com.java.projectfinal.model.entity.Genre;
import com.java.projectfinal.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    Optional<Movie> findByTitle(String title);

    Optional<Movie> findByGenre(Genre genre);

    List<Movie> findByTitleContainingIgnoreCase(String title);
}
