package com.att.tdp.popcorn_palace.controller;


import com.att.tdp.popcorn_palace.dto.ShowtimeRequestDTO;
import com.att.tdp.popcorn_palace.dto.ShowtimeResponseDTO;

import com.att.tdp.popcorn_palace.service.ShowtimeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {
    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }



    @GetMapping("/{showtimeId}")
    public ResponseEntity<ShowtimeResponseDTO> getShowtimeById(@PathVariable Long showtimeId) {
        return ResponseEntity.ok(showtimeService.getShowtimeById(showtimeId));
    }


    @PostMapping
    public ResponseEntity<ShowtimeResponseDTO> addShowtime(@Valid @RequestBody ShowtimeRequestDTO dto) {
        return ResponseEntity.ok(showtimeService.addShowtime(dto));
    }


    @PostMapping("/update/{showtimeId}")
    public ResponseEntity<Void> updateShowtime(
            @PathVariable Long showtimeId,
            @Valid @RequestBody ShowtimeRequestDTO  dto) {
        showtimeService.updateShowtime(showtimeId, dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{showtimeId}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long showtimeId) {
        showtimeService.deleteShowtime(showtimeId);
        return ResponseEntity.ok().build();
    }
}
