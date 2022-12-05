package ru.practicum.shareit.exception;

public class BookingStartOrEndErrorException extends RuntimeException{
    public BookingStartOrEndErrorException(String s) {
        super(s);
    }
}
