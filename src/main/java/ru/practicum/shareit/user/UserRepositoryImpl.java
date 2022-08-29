package ru.practicum.shareit.user;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public <S extends User> S save(S entity) {
        users.put(entity.getId(), entity);
        return entity;
    }

   /* @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    } */

    @Override
    public Optional<User> findById(Long aLong) {

        return Optional.of(users.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return users.containsKey(aLong);
    }

    @Override
    public Iterable<User> findAll() {
        return users.values();
    }

    /* @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public long count() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }*/

    @Override
    public void deleteById(Long aLong) {
        users.remove(aLong);
    }

    @Override
    public void delete(User entity) {
        users.remove(entity.getId());
    }

   /* @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void deleteAll() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    } */

    @Override
    public void update(User user, Long id) {
        users.replace(id, user);
    }
}
