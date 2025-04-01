package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.dto.MovieRequestDTO;
import com.att.tdp.popcorn_palace.dto.MovieResponseDTO;
import com.att.tdp.popcorn_palace.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This controller handles movie-related requests like adding, updating, deleting, and fetching movies

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> addMovie(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        return ResponseEntity.ok(movieService.addMovie(movieRequestDTO));
    }

    @PostMapping("/update/{movieTitle}")
    public ResponseEntity<Void> updateMovie(
            @PathVariable String movieTitle,
            @Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        movieService.updateMovie(movieTitle, movieRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{movieTitle}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String movieTitle) {
        movieService.deleteMovie(movieTitle);
        return ResponseEntity.ok().build();
    }
}
