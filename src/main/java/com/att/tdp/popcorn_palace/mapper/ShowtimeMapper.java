package com.att.tdp.popcorn_palace.mapper;

import com.att.tdp.popcorn_palace.dto.ShowtimeRequestDTO;
import com.att.tdp.popcorn_palace.dto.ShowtimeResponseDTO;
import com.att.tdp.popcorn_palace.entity.Showtime;

import java.util.List;

public class ShowtimeMapper {

    public static Showtime toEntity(ShowtimeRequestDTO dto){
        return new Showtime(
                dto.getMovieId(),
                dto.getPrice(),
                dto.getTheater(),
                dto.getStartTime(),
                dto.getEndTime()
        );
    }

    public static ShowtimeResponseDTO toDTO(Showtime entity){
        return new ShowtimeResponseDTO(
                entity.getId(),
                entity.getMovieId(),
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

    public static void updateEntity(Showtime entity, ShowtimeRequestDTO dto) {
        entity.setMovieId(dto.getMovieId());
        entity.setPrice(dto.getPrice());
        entity.setTheater(dto.getTheater());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
    }

}
