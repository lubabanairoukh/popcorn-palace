package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.BookingRequestDTO;
import com.att.tdp.popcorn_palace.dto.BookingResponseDTO;
import com.att.tdp.popcorn_palace.entity.Booking;
import com.att.tdp.popcorn_palace.entity.Showtime;
import com.att.tdp.popcorn_palace.exception.SeatAlreadyBookedException;
import com.att.tdp.popcorn_palace.exception.ShowtimeNotFoundException;
import com.att.tdp.popcorn_palace.mapper.BookingMapper;
import com.att.tdp.popcorn_palace.repository.BookingRepository;
import com.att.tdp.popcorn_palace.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;


// This service handles ticket bookings and checks if a seat is already taken
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ShowtimeRepository showtimeRepository;

    public BookingService(BookingRepository bookingRepository, ShowtimeRepository showtimeRepository) {
        this.bookingRepository = bookingRepository;
        this.showtimeRepository = showtimeRepository;
    }

    public BookingResponseDTO bookTicket(BookingRequestDTO dto) {

        Showtime showtime = showtimeRepository.findById(dto.getShowtime())
                .orElseThrow(() -> new ShowtimeNotFoundException(dto.getShowtime()));



        bookingRepository.findByShowtime_IdAndSeatNumber(dto.getShowtime(), dto.getSeatNumber())
                .ifPresent(existing -> {
                    throw new SeatAlreadyBookedException(dto.getSeatNumber());
                });


        Booking booking = BookingMapper.toBooking(dto, showtime);

        showtime.getBookings().add(booking);


        Booking saved = bookingRepository.save(booking);


        return BookingMapper.toBookingResponseDTO(saved);
    }

}
