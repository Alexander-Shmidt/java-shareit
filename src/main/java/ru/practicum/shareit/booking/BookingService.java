package ru.practicum.shareit.booking;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    ResponseEntity<Booking> reserved(Long userId, BookingDto bookingDto);

    ResponseEntity<Booking> approvedBooking(Long userId, Long bookingId, String approved);

    ResponseEntity<Booking> bookingInfo(Long userId, Long bookingId);
}
