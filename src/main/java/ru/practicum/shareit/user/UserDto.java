package ru.practicum.shareit.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    // @NotBlank(groups = {New.class, UpdateName.class},message = "Имя/логин не могут быть пустыми")
    // @NotNull(groups = {New.class, UpdateName.class},message = "Это поле должно быть заполнено")
    private String name; // имя или логин пользователя

    // @NotNull(groups = {New.class, UpdateName.class})
    // @Email(groups = {New.class, UpdateName.class},message = "Проверьте корректность ввода e-mail")
    private String email; // адрес электронной почты
}
