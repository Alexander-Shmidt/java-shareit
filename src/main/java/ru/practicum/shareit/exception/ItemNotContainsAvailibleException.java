package ru.practicum.shareit.exception;

public class ItemNotContainsAvailibleException extends RuntimeException{
    public ItemNotContainsAvailibleException (String s) {
        super(s);
    }
}
