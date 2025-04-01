package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.MovieRequestDTO;
import com.att.tdp.popcorn_palace.dto.MovieResponseDTO;
import com.att.tdp.popcorn_palace.entity.Movie;
import com.att.tdp.popcorn_palace.exception.MovieAlreadyExistsException;
import com.att.tdp.popcorn_palace.exception.MovieNotFoundException;
import com.att.tdp.popcorn_palace.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie movie;
    private MovieRequestDTO movieRequestDTO;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Sci-Fi");
        movie.setDuration(148);
        movie.setRating(8.8f);
        movie.setReleaseYear(2010);

        movieRequestDTO = new MovieRequestDTO();
        movieRequestDTO.setTitle("Inception");
        movieRequestDTO.setGenre("Sci-Fi");
        movieRequestDTO.setDuration(148);
        movieRequestDTO.setRating(8.8f);
        movieRequestDTO.setReleaseYear(2010);
    }

    @Test
    void addMovie_shouldSaveMovieSuccessfully() {
        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.empty());
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        MovieResponseDTO response = movieService.addMovie(movieRequestDTO);

        assertEquals("Inception", response.getTitle());
        verify(movieRepository).save(any(Movie.class));
    }

    @Test
    void addMovie_shouldThrowIfMovieAlreadyExists() {
        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.of(movie));

        assertThrows(MovieAlreadyExistsException.class, () -> {
            movieService.addMovie(movieRequestDTO);
        });

        verify(movieRepository, never()).save(any());
    }

    @Test
    void deleteMovie_shouldDeleteMovieSuccessfully() {
        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.of(movie));

        movieService.deleteMovie("Inception");

        verify(movieRepository).delete(movie);
    }

    @Test
    void deleteMovie_shouldThrowIfMovieNotFound() {
        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> {
            movieService.deleteMovie("Inception");
        });
    }

    @Test
    void updateMovie_shouldUpdateMovieSuccessfully() {
        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.of(movie));

        movieRequestDTO.setGenre("Action");

        movieService.updateMovie("Inception", movieRequestDTO);

        verify(movieRepository).save(any(Movie.class));
    }

    @Test
    void updateMovie_shouldThrowIfTitleChangedAndExists() {
        Movie anotherMovie = new Movie();
        anotherMovie.setTitle("Avatar");

        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.of(movie));
        when(movieRepository.findByTitle("Avatar")).thenReturn(Optional.of(anotherMovie));

        movieRequestDTO.setTitle("Avatar");

        assertThrows(MovieAlreadyExistsException.class, () -> {
            movieService.updateMovie("Inception", movieRequestDTO);
        });
    }
}