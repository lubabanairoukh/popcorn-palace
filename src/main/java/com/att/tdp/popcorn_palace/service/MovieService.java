package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.MovieRequestDTO;
import com.att.tdp.popcorn_palace.entity.Movie;
import com.att.tdp.popcorn_palace.dto.MovieResponseDTO;
import com.att.tdp.popcorn_palace.exception.MovieAlreadyExistsException;
import com.att.tdp.popcorn_palace.exception.MovieNotFoundException;
import com.att.tdp.popcorn_palace.mapper.MovieMapper;
import com.att.tdp.popcorn_palace.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Manages movie operations like adding, updating, deleting, and fetching from the database
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponseDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return MovieMapper.toDTOList(movies);
    }

    public MovieResponseDTO addMovie(MovieRequestDTO dto) {
        if (movieRepository.findByTitle(dto.getTitle()).isPresent()) {
            throw new MovieAlreadyExistsException(dto.getTitle());
        }

        Movie movie = MovieMapper.toEntity(dto);
        Movie saved = movieRepository.save(movie);
        return MovieMapper.toDTO(saved);
    }

    @Transactional
    public void deleteMovie(String title) {
        Movie movie = findMovieByTitleOrThrow(title);
        movieRepository.delete(movie);
    }

    public void updateMovie(String title, MovieRequestDTO movieRequestDTO) {
        Movie existingMovie = findMovieByTitleOrThrow(title);

        if (!movieRequestDTO.getTitle().equals(title)) {
            movieRepository.findByTitle(movieRequestDTO.getTitle()).ifPresent(duplicate -> {
                throw new MovieAlreadyExistsException(movieRequestDTO.getTitle());
            });
        }

        MovieMapper.updateEntity(existingMovie, movieRequestDTO);
        movieRepository.save(existingMovie);
    }

    private Movie findMovieByTitleOrThrow(String title) {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundException(title));
    }


    public Movie findMovieByIdOrThrow(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with ID " + id + " not found."));
    }

}
