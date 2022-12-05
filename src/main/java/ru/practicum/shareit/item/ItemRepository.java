package ru.practicum.shareit.item;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findItemsByOwner(Long owner);
    Item findItemByOwner(Long owner);

    Long findItemByOwnerAndName(Long owner, String name);
}
