package ru.practicum.shareit.user;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void save(UserDto userDto);

    User getUser(Long id);

    void deleteUser(Long userId);

    void update(UserDto userDto);

    void update(UserDto userDto, long userId);

    User getUserByName(String name);
}
