package ru.practicum.shareit.validators;

import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.UserDto;

public class Validator {
    public static void updateUserValidation(UserDto userDto) {
        if ((userDto.getEmail() == null || userDto.getEmail().isBlank() || !userDto.getEmail().contains("@")) &&
                (userDto.getName().isEmpty() || userDto.getName() == null)) {
            throw new ValidationException("Нет данных для обновления.");
        }
    }
}
