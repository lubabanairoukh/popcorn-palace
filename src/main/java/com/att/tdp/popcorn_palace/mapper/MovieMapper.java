package com.att.tdp.popcorn_palace.mapper;

import com.att.tdp.popcorn_palace.dto.MovieRequestDTO;
import com.att.tdp.popcorn_palace.dto.MovieResponseDTO;
import com.att.tdp.popcorn_palace.entity.Movie;

import java.util.List;


// Converts movie details between DTOs and the Movie entity, and updates movie data
public class MovieMapper {
    public static Movie toEntity(MovieRequestDTO dto){
        return new Movie(
                dto.getTitle(),
                dto.getGenre(),
                dto.getDuration(),
                dto.getRating(),
                dto.getReleaseYear()
        );

    }
    public static MovieResponseDTO toDTO(Movie movie){
        return new MovieResponseDTO(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getDuration(),
                movie.getRating(),
                movie.getReleaseYear()
        );
    }
    public static List<MovieResponseDTO> toDTOList(List<Movie> movies){
        return movies.stream()
                .map(MovieMapper::toDTO)
                .toList();
    }
    public static void updateEntity(Movie movie, MovieRequestDTO dto) {
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setDuration(dto.getDuration());
        movie.setRating(dto.getRating());
        movie.setReleaseYear(dto.getReleaseYear());
    }


}
