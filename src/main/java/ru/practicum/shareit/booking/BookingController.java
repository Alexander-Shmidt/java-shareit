package ru.practicum.shareit.booking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * // TODO .
 */
@RestController
@Slf4j
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> reserved(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestBody BookingDto bookingDto) {

        return bookingService.reserved(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}?approved={approved}")
    public ResponseEntity approvedBooking(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId,
                                                   @RequestParam String approved) {
        return bookingService.approvedBooking(userId, bookingId, approved);
    }

   @GetMapping("/{bookingId}")
   public ResponseEntity<Booking> findBooking(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId) {
        return bookingService.bookingInfo(userId, bookingId);
    }

   @GetMapping("/bookings?state={state}")
   public Collection<Booking> findAllBookingThisUser(@RequestHeader("X-Sharer-User-Id") Long userId, State state) {
        return null;
    }
}
