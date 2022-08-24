package ru.practicum.shareit.user;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail()
        );
    }

    public static User toUser(UserDto userDto, Long id) {
        return new User(
                id,
                userDto.getName(),
                userDto.getEmail()
        );
    }
}
