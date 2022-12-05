package ru.practicum.shareit.user;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Iterable<User> getAllUsers();

    void save(UserDto userDto);

    User getUser(Long id);

    void deleteUser(Long userId);

    void update(UserDto userDto);

    void update(UserDto userDto, long userId);

    Optional<User> findUserByName(String name);
}
