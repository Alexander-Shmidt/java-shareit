package ru.practicum.shareit.validators;

import ru.practicum.shareit.exception.ItemNotContainsAvailibleException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.ItemDto;
import ru.practicum.shareit.user.UserDto;

public class Validator {
    public void addOrUpdateUserValidation(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isBlank() || !userDto.getEmail().contains("@")) {
            throw new ValidationException("Адрес электронной почты указан неверно.");
        }

        if (userDto.getName().isEmpty() || userDto.getName() == null) {
            throw new ValidationException("Имя пользователя задано неверно.");
        }
    }

    public void updateUserValidation(UserDto userDto) {
        if ((userDto.getEmail() == null || userDto.getEmail().isBlank() || !userDto.getEmail().contains("@")) &&
                (userDto.getName().isEmpty() || userDto.getName() == null)) {
            throw new ValidationException("Нет данных для обновления.");
        }
    }

    public void addItemValidation(ItemDto itemDto) {
        if (itemDto.getName() == null || itemDto.getName().isBlank())
            throw new ValidationException("Отсутствует название вещи");
        if (itemDto.getAvailable() == null) {
            throw new ItemNotContainsAvailibleException("Отсутствует поле Availible");
        }
        if (itemDto.getDescription() == null || itemDto.getDescription().isEmpty()) {
            throw new ItemNotContainsAvailibleException("Отсутствует описание вещи");
        }
    }
}
