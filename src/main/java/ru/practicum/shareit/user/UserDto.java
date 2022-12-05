package ru.practicum.shareit.user;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.transfer.New;
import ru.practicum.shareit.transfer.UpdateName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class UserDto {
    @NotBlank(groups = {New.class},message = "Имя/логин не могут быть пустыми")
    @NotNull(groups = {New.class},message = "Это поле должно быть заполнено")
    @Null(groups = {UpdateName.class})
    private String name; // имя или логин пользователя

    @NotNull(groups = {New.class})
    @Email(groups = {New.class},message = "Проверьте корректность ввода e-mail")
    @Null(groups = {UpdateName.class})
    private String email; // адрес электронной почты

}
