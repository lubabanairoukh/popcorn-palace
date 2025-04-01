package com.att.tdp.popcorn_palace.mapper;

import com.att.tdp.popcorn_palace.dto.BookingRequestDTO;
import com.att.tdp.popcorn_palace.dto.BookingResponseDTO;
import com.att.tdp.popcorn_palace.entity.Booking;
import com.att.tdp.popcorn_palace.entity.Showtime;

import java.util.UUID;

public class BookingMapper {


    public static Booking toBooking(BookingRequestDTO dto, Showtime showtime) {
        Booking booking = new Booking();

        booking.setSeatNumber(dto.getSeatNumber());
        booking.setUserId(dto.getUserId());
        booking.setShowtime(showtime);
        return booking;
    }

    public static BookingResponseDTO toBookingResponseDTO(Booking booking) {
        return new BookingResponseDTO(booking.getBookingId());
    }
}
