package ru.practicum.shareit.user;

import java.util.Optional;

public interface UserRepository {
    <S extends User> S save(S entity);

    Optional<User> findById(Long aLong);

    boolean existsById(Long aLong);

    Iterable<User> findAll();

    void deleteById(Long aLong);

    void delete(User entity);

    void update(User user, Long id);
}
