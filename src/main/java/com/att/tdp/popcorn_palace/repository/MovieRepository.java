package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// Manages movie data in the database, including searching and deleting by title
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
    void deleteByTitle(String title);
}

