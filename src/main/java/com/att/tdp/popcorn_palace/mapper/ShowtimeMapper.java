package com.att.tdp.popcorn_palace.mapper;

import com.att.tdp.popcorn_palace.dto.ShowtimeRequestDTO;
import com.att.tdp.popcorn_palace.dto.ShowtimeResponseDTO;
import com.att.tdp.popcorn_palace.entity.Movie;
import com.att.tdp.popcorn_palace.entity.Showtime;

import java.util.List;


// Converts showtime data between DTOs and the Showtime entity, and handles updates
public class ShowtimeMapper {

    public static Showtime toEntity(ShowtimeRequestDTO dto, Movie movie){
        return new Showtime(
                movie,
                dto.getPrice(),
                dto.getTheater(),
                dto.getStartTime(),
                dto.getEndTime()
        );
    }

    public static ShowtimeResponseDTO toDTO(Showtime entity){
        return new ShowtimeResponseDTO(
                entity.getId(),
                entity.getMovie().getMovieId(),
                entity.getPrice(),
                entity.getTheater(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }

    public static List<ShowtimeResponseDTO> toDTOList(List<Showtime> entities) {
        return entities.stream()
                .map(ShowtimeMapper::toDTO)
                .toList();
    }

    public static void updateEntity(Showtime entity, ShowtimeRequestDTO dto, Movie movie) {
        entity.setMovie(movie);
        entity.setPrice(dto.getPrice());
        entity.setTheater(dto.getTheater());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
    }

}
