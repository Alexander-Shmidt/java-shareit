package ru.practicum.shareit.booking;

public class BookingMapper {
    public static Booking toBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setStart(bookingDto.getStart());
        booking.setEnd(bookingDto.getEnd());
        return booking;
    }

    public static BookingDtoOut toBookingDtoOut(Booking booking) {
        BookingDtoOut bookingDtoOut = new BookingDtoOut();
        bookingDtoOut.setId(booking.getId());
        bookingDtoOut.setStart(booking.getStart());
        bookingDtoOut.setEnd(booking.getEnd());
        bookingDtoOut.setStatus(booking.getStatus());
        return bookingDtoOut;
    }
}
