package ru.practicum.shareit.item;

import java.util.Optional;

public interface ItemRepository {
    <S extends Item> S save(S entity);

    Iterable<Item> findAll();

    Optional<Item> findById(Long itemId);

    boolean existsById(Long aLong);

    boolean findOwner(Long owner);

    void update(Long itemId, Item item);
}
