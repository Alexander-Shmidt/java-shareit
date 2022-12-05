package ru.practicum.shareit.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail()
        );
    }

    public static List<UserDto> toUserDto(Iterable<User> users) {
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(toUserDto(user));
        }

        return result;
    }

    public static User toUser(UserDto userDto) {
        return new User(
                userDto.getName(),
                userDto.getEmail()
        );
    }
}
